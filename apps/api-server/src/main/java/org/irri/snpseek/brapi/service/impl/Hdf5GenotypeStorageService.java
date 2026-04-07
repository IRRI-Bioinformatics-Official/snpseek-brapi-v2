package org.irri.snpseek.brapi.service.impl;

import io.jhdf.HdfFile;
import io.jhdf.api.Dataset;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityNotFoundException;
import org.irri.snpseek.brapi.domain.GenotypeRun;
import org.irri.snpseek.brapi.domain.VariantSet;
import org.irri.snpseek.brapi.repository.VariantSetRepository;
import org.irri.snpseek.brapi.service.GenotypeStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * {@link GenotypeStorageService} backed by <a href="https://github.com/jamesmudd/jhdf">jhdf</a>,
 * a pure-Java HDF5 library available on Maven Central.
 *
 * <h3>Memory strategy</h3>
 * <p>jhdf reads HDF5 files via {@code java.nio.channels.FileChannel.map()}, so the OS
 * manages which pages of the file are in physical RAM — only the pages touched by a
 * {@code dataset.getData(offset, shape)} call are ever faulted in.  For a 20 M-row ×
 * 3 000-variety matrix this means a sub-matrix request loads at most
 * {@code snpChunkSize × nVarieties × 1 byte ≈ 30 MB} at a time, not the full file.
 *
 * <h3>Thread safety</h3>
 * <p>Each open file is wrapped in a {@link ReaderHandle} with a {@link ReentrantLock}.
 * Concurrent requests for different datasets proceed in parallel; requests for the
 * same dataset are serialised through their shared lock.
 */
@Service
public class Hdf5GenotypeStorageService implements GenotypeStorageService {

    private static final Logger log = LoggerFactory.getLogger(Hdf5GenotypeStorageService.class);

    /** Name of the 2-D genotype dataset inside every HDF5 file. */
    static final String MATRIX_DATASET = "matrix";

    /**
     * SNPseek genotype encoding (byte value → VCF GT string):
     *   0 → 0/0  1 → 0/1  2 → 1/1  3 → ./.
     */
    private static final String[] GENOTYPE_LABELS = {"0/0", "0/1", "1/1", "./."};

    @Value("${brapi.hdf5.snp-chunk-size:10000}")
    private int snpChunkSize;

    @Value("${brapi.hdf5.data-dir}")
    private String hdf5DataDir;

    private final VariantSetRepository variantSetRepository;

    private final ConcurrentHashMap<String, ReaderHandle> readerCache = new ConcurrentHashMap<>();

    public Hdf5GenotypeStorageService(VariantSetRepository variantSetRepository) {
        this.variantSetRepository = variantSetRepository;
    }

    // -------------------------------------------------------------------------
    // Reader handle — one open HdfFile per path, access serialised by lock
    // -------------------------------------------------------------------------

    private static final class ReaderHandle implements AutoCloseable {

        final HdfFile   hdfFile;
        final Dataset   dataset;   // cached — avoids repeated path resolution
        final ReentrantLock lock = new ReentrantLock();
        final long totalSnps;
        final long totalVarieties;

        ReaderHandle(String filePath) {
            File file = new File(filePath);
            if (!file.isFile() || !file.canRead()) {
                throw new IllegalStateException("HDF5 file is not readable: " + filePath);
            }
            this.hdfFile = new HdfFile(file);
            this.dataset = hdfFile.getDatasetByPath(MATRIX_DATASET);
            int[] dims   = this.dataset.getDimensions(); // jhdf returns int[], not long[]
            if (dims.length != 2) {
                hdfFile.close();
                throw new IllegalStateException(
                    "Expected a 2-D 'matrix' dataset in " + filePath
                    + ", got rank " + dims.length);
            }
            this.totalSnps      = dims[0];   // widened int → long
            this.totalVarieties = dims[1];
            log.info("Opened HDF5 '{}': {} SNPs × {} varieties", filePath, totalSnps, totalVarieties);
        }

        <T> T locked(Supplier<T> action) {
            lock.lock();
            try   { return action.get(); }
            finally { lock.unlock(); }
        }

        @Override
        public void close() {
            lock.lock();
            try   { hdfFile.close(); }
            finally { lock.unlock(); }
        }
    }

    /** Resolve the HDF5 path from the variantset → platform → genotype_run chain. */
    private ReaderHandle handleFor(Long variantSetDbId) {
        VariantSet vs = variantSetRepository.findByIdWithGenotypeRuns(variantSetDbId.intValue())
            .orElseThrow(() -> new EntityNotFoundException("VariantSet not found: " + variantSetDbId));

        String dataLocation = vs.getPlatforms().stream()
            .flatMap(p -> p.getGenotypeRuns().stream())
            .filter(GenotypeRun::isVisible)
            .map(GenotypeRun::getDataLocation)
            .filter(loc -> loc != null && !loc.isBlank())
            .findFirst()
            .orElseThrow(() -> new IllegalStateException(
                "VariantSet " + variantSetDbId
                + " has no visible genotype_run with a data_location"));

        return readerCache.computeIfAbsent(hdf5DataDir + "/" + dataLocation, ReaderHandle::new);
    }

    @PreDestroy
    void shutdown() {
        log.info("Closing {} cached HDF5 reader(s)", readerCache.size());
        readerCache.values().forEach(h -> {
            try { h.close(); }
            catch (Exception e) { log.warn("Error closing HDF5 reader on shutdown", e); }
        });
        readerCache.clear();
    }

    // -------------------------------------------------------------------------
    // Sub-matrix fetch
    // -------------------------------------------------------------------------

    @Override
    public SubMatrix fetchSubMatrix(
            Long variantSetDbId,
            long snpStart,
            long snpEnd,
            List<Integer> varietyIndexes) {

        ReaderHandle h = handleFor(variantSetDbId);
        int totalCols  = (int) h.totalVarieties;

        validateSubMatrixArgs(snpStart, snpEnd, h.totalSnps, varietyIndexes, totalCols);

        int nSnpRows   = (int)(snpEnd - snpStart);
        int nVarieties = varietyIndexes.size();
        byte[][] result = new byte[nSnpRows][nVarieties];

        for (long chunkStart = snpStart; chunkStart < snpEnd; chunkStart += snpChunkSize) {
            long chunkEnd = Math.min(chunkStart + snpChunkSize, snpEnd);
            int  chunkLen = (int)(chunkEnd - chunkStart);
            final long rowOffset = chunkStart;

            byte[] flat = h.locked(() -> {
                Object raw = h.dataset.getData(
                    new long[]{rowOffset, 0},
                    new int[] {chunkLen,  totalCols}
                );
                return toFlatBytes(raw, chunkLen, totalCols);
            });

            int destRowBase = (int)(chunkStart - snpStart);
            for (int row = 0; row < chunkLen; row++) {
                int srcBase = row * totalCols;
                for (int v = 0; v < nVarieties; v++) {
                    result[destRowBase + row][v] = flat[srcBase + varietyIndexes.get(v)];
                }
            }
        }

        return new SubMatrix(snpStart, snpEnd, List.copyOf(varietyIndexes), result);
    }

    private void validateSubMatrixArgs(
            long snpStart, long snpEnd, long totalSnps,
            List<Integer> varietyIndexes, int totalCols) {
        if (snpStart < 0 || snpStart >= snpEnd)
            throw new IllegalArgumentException(
                "Invalid SNP range: snpStart=%d snpEnd=%d".formatted(snpStart, snpEnd));
        if (snpEnd > totalSnps)
            throw new IllegalArgumentException(
                "snpEnd=%d exceeds dataset size %d".formatted(snpEnd, totalSnps));
        if (varietyIndexes == null || varietyIndexes.isEmpty())
            throw new IllegalArgumentException("varietyIndexes must not be null or empty");
        for (int vi : varietyIndexes)
            if (vi < 0 || vi >= totalCols)
                throw new IllegalArgumentException(
                    "Variety index %d out of range [0, %d)".formatted(vi, totalCols));
    }

    // -------------------------------------------------------------------------
    // Streaming — full VariantSet
    // -------------------------------------------------------------------------

    @Override
    public Stream<GenotypeCall> streamCallsByVariantSet(Long variantSetDbId) {
        ReaderHandle h   = handleFor(variantSetDbId);
        int  totalCols   = (int) h.totalVarieties;
        long totalChunks = (h.totalSnps + snpChunkSize - 1) / snpChunkSize;

        return LongStream.range(0, totalChunks)
            .boxed()
            .flatMap(chunkIdx -> {
                long snpStart = chunkIdx * snpChunkSize;
                long snpEnd   = Math.min(snpStart + snpChunkSize, h.totalSnps);
                int  chunkLen = (int)(snpEnd - snpStart);

                byte[] flat = h.locked(() -> {
                    Object raw = h.dataset.getData(
                        new long[]{snpStart, 0},
                        new int[] {chunkLen,  totalCols}
                    );
                    return toFlatBytes(raw, chunkLen, totalCols);
                });

                return IntStream.range(0, chunkLen * totalCols)
                    .mapToObj(i -> new GenotypeCall(
                        snpStart + (i / totalCols),
                        i % totalCols,
                        decodeGenotype(flat[i]),
                        snpStart + (i / totalCols),
                        i % totalCols
                    ));
            });
    }

    // -------------------------------------------------------------------------
    // Pagination
    // -------------------------------------------------------------------------

    @Override
    public GenotypeCallPage pageCallsByVariantSet(Long variantSetDbId, Pageable pageable) {
        ReaderHandle h    = handleFor(variantSetDbId);
        int  totalCols    = (int) h.totalVarieties;
        long pageSnpStart = (long) pageable.getPageNumber() * pageable.getPageSize();

        if (pageSnpStart >= h.totalSnps)
            return new GenotypeCallPage(List.of(), pageable.getPageNumber(),
                                        pageable.getPageSize(), h.totalSnps);

        long pageSnpEnd = Math.min(pageSnpStart + pageable.getPageSize(), h.totalSnps);
        int  snpsOnPage = (int)(pageSnpEnd - pageSnpStart);
        final long offset = pageSnpStart;

        byte[] flat = h.locked(() -> {
            Object raw = h.dataset.getData(
                new long[]{offset, 0},
                new int[] {snpsOnPage, totalCols}
            );
            return toFlatBytes(raw, snpsOnPage, totalCols);
        });

        List<GenotypeCall> calls = new ArrayList<>(snpsOnPage * totalCols);
        for (int row = 0; row < snpsOnPage; row++)
            for (int col = 0; col < totalCols; col++)
                calls.add(new GenotypeCall(
                    pageSnpStart + row, col,
                    decodeGenotype(flat[row * totalCols + col]),
                    pageSnpStart + row, col
                ));

        return new GenotypeCallPage(calls, pageable.getPageNumber(),
                                    pageable.getPageSize(), h.totalSnps);
    }

    // -------------------------------------------------------------------------
    // Formats
    // -------------------------------------------------------------------------

    @Override
    public String[] availableFormats(Long variantSetDbId) {
        return new String[]{"application/x-hdf5", "text/tab-separated-values"};
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    /**
     * Flatten the Object returned by jhdf's {@code getData()} into a row-major
     * {@code byte[]}.
     *
     * <p>jhdf maps HDF5 native types to Java arrays as follows:
     * <ul>
     *   <li>HDF5 {@code int8}  → {@code byte[][]}</li>
     *   <li>HDF5 {@code uint8} → {@code short[][]} (widened to preserve unsigned range)</li>
     * </ul>
     * SNPseek genotype codes (0–3) fit in a signed byte, so the narrowing cast
     * from {@code short} back to {@code byte} is safe.
     */
    private static byte[] toFlatBytes(Object raw, int rows, int cols) {
        byte[] flat = new byte[rows * cols];
        if (raw instanceof byte[][] grid) {
            for (int r = 0; r < rows; r++)
                System.arraycopy(grid[r], 0, flat, r * cols, cols);
        } else if (raw instanceof short[][] grid) {
            for (int r = 0; r < rows; r++)
                for (int c = 0; c < cols; c++)
                    flat[r * cols + c] = (byte) grid[r][c];
        } else {
            throw new IllegalStateException(
                "'" + MATRIX_DATASET + "' returned unexpected Java type: "
                + raw.getClass().getName()
                + " — expected byte[][] (int8) or short[][] (uint8)");
        }
        return flat;
    }

    private static String decodeGenotype(byte rawValue) {
        int idx = rawValue & 0xFF; // treat as unsigned
        return idx < GENOTYPE_LABELS.length ? GENOTYPE_LABELS[idx] : "./.";
    }

}
