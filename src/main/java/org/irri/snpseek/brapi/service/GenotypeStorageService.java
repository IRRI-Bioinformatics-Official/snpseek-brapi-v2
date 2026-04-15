package org.irri.snpseek.brapi.service;

import org.irri.snpseek.brapi.domain.VariantSet;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Stream;

/**
 * Strategy interface for reading genotype calls from SNPseek's binary storage.
 *
 * <p>SNPseek persists its genotype matrix outside of PostgreSQL — typically as
 * an HDF5 or NumPy-compatible array where rows are variant positions and
 * columns are samples.  With 20 M+ rows the data cannot be loaded into memory
 * at once; all read methods therefore return a lazy {@link Stream} or support
 * page-based slicing via {@link Pageable}.
 *
 * <p>Implementations are expected to:
 * <ul>
 *   <li>Open the backing file referenced via the variantset → platform → genotype_run
 *       chain on demand and close it when the returned stream is closed.</li>
 *   <li>Use Java NIO or a native library (e.g. jhdf, netCDF-Java) to read
 *       only the requested slice of the matrix.</li>
 *   <li>Be thread-safe — multiple concurrent requests may arrive for the same
 *       dataset.</li>
 * </ul>
 */
public interface GenotypeStorageService {

    // -------------------------------------------------------------------------
    // Streaming reads (preferred for large result sets)
    // -------------------------------------------------------------------------

    /**
     * Stream every genotype call in a VariantSet, ordered by variant position
     * then sample index.
     *
     * <p>The caller is responsible for closing the returned stream (try-with-resources).
     *
     * @param variantSetDbId PK of the target {@link VariantSet}
     * @return lazy {@link Stream} of {@link GenotypeCall} records; never null
     */
    Stream<GenotypeCall> streamCallsByVariantSet(Long variantSetDbId);

    // -------------------------------------------------------------------------
    // Paginated reads (used by BrAPI /calls endpoint)
    // -------------------------------------------------------------------------

    /**
     * Return a single page of genotype calls for a VariantSet.
     *
     * @param variantSetDbId PK of the target {@link VariantSet}
     * @param pageable       page number, size, and optional sort
     * @return {@link GenotypeCallPage} containing the requested slice and
     *         total-count metadata for BrAPI pagination envelopes
     */
    GenotypeCallPage pageCallsByVariantSet(Long variantSetDbId, Pageable pageable);

    // -------------------------------------------------------------------------
    // Sub-matrix fetch (direct HDF5 hyperslab access)
    // -------------------------------------------------------------------------

    /**
     * Fetch a rectangular sub-matrix from the backing HDF5 genotype file without
     * loading the full dataset into memory.
     *
     * <p>Rows are selected by a contiguous SNP-index range {@code [snpStart, snpEnd)};
     * columns by an arbitrary list of 0-based variety (sample) indexes.
     * Implementations must read only the required HDF5 chunks from disk.
     *
     * @param variantSetDbId PK of the target {@link VariantSet} (used to locate the HDF5 file)
     * @param snpStart       inclusive start row index (0-based)
     * @param snpEnd         exclusive end row index
     * @param varietyIndexes ordered list of 0-based column indexes to include; must not be empty
     * @return {@link SubMatrix} wrapping the raw byte calls and index metadata
     */
    SubMatrix fetchSubMatrix(Long variantSetDbId, long snpStart, long snpEnd, List<Integer> varietyIndexes);

    // -------------------------------------------------------------------------
    // Metadata
    // -------------------------------------------------------------------------

    /**
     * Return the formats (MIME types / encoding labels) in which the backing
     * binary file can be served.  Used to populate BrAPI
     * {@code VariantSet.availableFormats}.
     *
     * @param variantSetDbId PK of the target {@link VariantSet}
     * @return array of format descriptors, e.g. {@code ["application/x-hdf5", "text/tsv"]}
     */
    String[] availableFormats(Long variantSetDbId);

    // -------------------------------------------------------------------------
    // Nested value types
    // -------------------------------------------------------------------------

    /**
     * A single decoded genotype call as returned by the storage layer.
     *
     * <p>The {@code genotypeValue} is the raw allele-encoded string stored in
     * the binary matrix (e.g. {@code "0/0"}, {@code "0/1"}, {@code "."}).
     * Higher layers translate this into BrAPI {@code Call} objects.
     */
    record GenotypeCall(
        long   variantDbId,
        long   callSetDbId,
        String genotypeValue,
        /** Row index within the binary matrix (0-based). */
        long   matrixRowIndex,
        /** Column index within the binary matrix (0-based). */
        int    matrixColIndex
    ) {}

    /**
     * A rectangular sub-matrix of raw genotype bytes returned by
     * {@link #fetchSubMatrix}.
     *
     * <p>Encoding (SNPseek convention):
     * <pre>
     *   0 → "0/0"  homozygous reference
     *   1 → "0/1"  heterozygous
     *   2 → "1/1"  homozygous alternate
     *   3 → "./."  missing / no call
     * </pre>
     *
     * @param snpStart       inclusive start row used to produce this matrix
     * @param snpEnd         exclusive end row
     * @param varietyIndexes the column indexes requested (same order as {@code rawCalls} columns)
     * @param rawCalls       {@code rawCalls[snpIdx][varietyIdx]} = raw genotype byte
     */
    record SubMatrix(
        long         snpStart,
        long         snpEnd,
        List<Integer> varietyIndexes,
        byte[][]     rawCalls
    ) {
        private static final String[] LABELS = {"0/0", "0/1", "1/1", "./."};

        /** Number of SNP rows in this sub-matrix. */
        public int snpCount()      { return rawCalls.length; }

        /** Number of variety columns in this sub-matrix. */
        public int varietyCount()  { return rawCalls.length == 0 ? 0 : rawCalls[0].length; }

        /**
         * Decode the raw byte matrix to VCF-style genotype strings.
         * Allocates a new {@code String[][]} — use sparingly on large sub-matrices.
         */
        public String[][] decoded() {
            String[][] out = new String[snpCount()][varietyCount()];
            for (int s = 0; s < snpCount(); s++) {
                for (int v = 0; v < varietyCount(); v++) {
                    int idx = rawCalls[s][v] & 0xFF;
                    out[s][v] = idx < LABELS.length ? LABELS[idx] : "./.";
                }
            }
            return out;
        }
    }

    /**
     * A page of {@link GenotypeCall} records together with pagination metadata,
     * matching the shape expected by BrAPI list-response envelopes.
     *
     * @param calls      ordered list of calls on this page
     * @param pageNumber 0-based current page number
     * @param pageSize   requested page size
     * @param totalCount total number of calls across all pages
     */
    record GenotypeCallPage(
        java.util.List<GenotypeCall> calls,
        int  pageNumber,
        int  pageSize,
        long totalCount
    ) {
        public int totalPages() {
            return pageSize == 0 ? 0 : (int) Math.ceil((double) totalCount / pageSize);
        }
    }
}
