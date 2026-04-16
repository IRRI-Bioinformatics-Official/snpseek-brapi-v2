package org.irri.snpseek.brapi.service;

import jakarta.persistence.criteria.Predicate;
import org.irri.snpseek.brapi.domain.Germplasm;
import org.irri.snpseek.brapi.domain.SnpMetadata;
import org.irri.snpseek.brapi.domain.VariantSet;
import org.irri.snpseek.brapi.dto.AlleleMatrixDto;
import org.irri.snpseek.brapi.dto.AlleleMatrixDto.AlleleMatrixPagination;
import org.irri.snpseek.brapi.dto.AlleleMatrixDto.DataMatrix;
import org.irri.snpseek.brapi.dto.AlleleMatrixSearchRequest;
import org.irri.snpseek.brapi.repository.GermplasmRepository;
import org.irri.snpseek.brapi.repository.SnpMetadataRepository;
import org.irri.snpseek.brapi.repository.VariantSetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Resolves BrAPI AlleleMatrix queries by combining metadata from PostgreSQL
 * with genotype data from the HDF5 binary store.
 *
 * <h3>Query flow</h3>
 * <ol>
 *   <li>Resolve the first {@code variantSetDbId} to its {@link VariantSet#getName() datasetName}.</li>
 *   <li>Page through {@link SnpMetadata} filtered by {@code variantset = datasetName} (and optional
 *       variant filters), sorted by {@code allele_index}, to get the SNP page.</li>
 *   <li>Page through {@link Germplasm} filtered by {@code dataset = datasetName} (and optional
 *       callSet filters), sorted by {@code stock_sample_id}, to get the sample page.</li>
 *   <li>If either page is empty, return an empty matrix immediately.</li>
 *   <li>Determine the contiguous HDF5 row range [{@code snpStart}, {@code snpEnd}) from the
 *       min/max {@code allele_index} values.</li>
 *   <li>Call {@link GenotypeStorageService#fetchSubMatrix} with the variety index list.</li>
 *   <li>Build the BrAPI {@link DataMatrix} using the {@code alleleIndex → row} mapping.</li>
 * </ol>
 */
@Service
public class AlleleMatrixService {

    private static final Logger log = LoggerFactory.getLogger(AlleleMatrixService.class);

    private final SnpMetadataRepository  snpMetadataRepository;
    private final GermplasmRepository    germplasmRepository;
    private final VariantSetRepository   variantSetRepository;
    private final GenotypeStorageService storageService;

    public AlleleMatrixService(SnpMetadataRepository snpMetadataRepository,
                               GermplasmRepository germplasmRepository,
                               VariantSetRepository variantSetRepository,
                               GenotypeStorageService storageService) {
        this.snpMetadataRepository = snpMetadataRepository;
        this.germplasmRepository   = germplasmRepository;
        this.variantSetRepository  = variantSetRepository;
        this.storageService        = storageService;
    }

    // -------------------------------------------------------------------------
    // Main entry point
    // -------------------------------------------------------------------------

    /**
     * Execute an allele matrix query and return the result as an {@link AlleleMatrixDto}.
     *
     * @param req the search/filter parameters
     * @return populated {@link AlleleMatrixDto} (may be empty if no data matches)
     */
    public AlleleMatrixDto query(AlleleMatrixSearchRequest req) {

        // 1. Resolve variantSetDbId
        if (req.variantSetDbIds() == null || req.variantSetDbIds().isEmpty()) {
            return emptyMatrix(req, 0L, 0L);
        }
        String variantSetDbIdStr = req.variantSetDbIds().get(0);
        long   variantSetDbId;
        try {
            variantSetDbId = Long.parseLong(variantSetDbIdStr);
        } catch (NumberFormatException e) {
            return emptyMatrix(req, 0L, 0L);
        }

        Optional<VariantSet> vsOpt = variantSetRepository.findById((int) variantSetDbId);
        if (vsOpt.isEmpty()) {
            return emptyMatrix(req, 0L, 0L);
        }
        String datasetName = vsOpt.get().getName();

        // 2. Resolve SNPs (variant dimension)
        Specification<SnpMetadata> snpSpec  = buildSnpSpec(req, datasetName);
        long snpTotal = snpMetadataRepository.count(snpSpec);

        Page<SnpMetadata> snpPage = snpMetadataRepository.findAll(
                snpSpec,
                PageRequest.of(
                        req.effectiveVariantPage(),
                        req.effectiveVariantPageSize(),
                        Sort.by("alleleIndex")));

        // 3. Resolve CallSets (call set dimension)
        Specification<Germplasm> callSetSpec = buildCallSetSpec(req, datasetName);
        long callSetTotal = germplasmRepository.count(callSetSpec);

        Page<Germplasm> callSetPage = germplasmRepository.findAll(
                callSetSpec,
                PageRequest.of(
                        req.effectiveCallSetPage(),
                        req.effectiveCallSetPageSize(),
                        Sort.by("stockSampleId")));

        // 4. Empty guard
        if (snpPage.isEmpty() || callSetPage.isEmpty()) {
            return emptyMatrix(req, snpTotal, callSetTotal);
        }

        List<SnpMetadata> snps     = snpPage.getContent();
        List<Germplasm>   callSets = callSetPage.getContent();

        // 5. Determine HDF5 row range
        long snpStart = snps.stream()
                .map(SnpMetadata::getAlleleIndex)
                .filter(idx -> idx != null)
                .mapToLong(Long::longValue)
                .min()
                .orElse(0L);
        long snpEnd = snps.stream()
                .map(SnpMetadata::getAlleleIndex)
                .filter(idx -> idx != null)
                .mapToLong(Long::longValue)
                .max()
                .orElse(0L) + 1L;

        // 6. Build variety index list
        List<Integer> varietyIdxs = callSets.stream()
                .map(g -> g.getStockSampleId().intValue())
                .toList();

        // 7. Fetch sub-matrix (wrapped in try-catch for dev environments without HDF5)
        String[][] decoded;
        try {
            GenotypeStorageService.SubMatrix subMatrix =
                    storageService.fetchSubMatrix(variantSetDbId, snpStart, snpEnd, varietyIdxs);
            decoded = subMatrix.decoded();

            // Build alleleIndex → row mapping within the sub-matrix
            Map<Long, Integer> alleleIndexToRow = new HashMap<>();
            for (int i = 0; i < snps.size(); i++) {
                Long ai = snps.get(i).getAlleleIndex();
                if (ai != null) {
                    alleleIndexToRow.put(ai, (int)(ai - snpStart));
                }
            }

            // 8. Build ordered dataMatrix rows matching the snp page order
            List<List<String>> matrix = new ArrayList<>(snps.size());
            for (SnpMetadata snp : snps) {
                Long ai = snp.getAlleleIndex();
                if (ai == null || !alleleIndexToRow.containsKey(ai)) {
                    List<String> missingRow = new ArrayList<>(callSets.size());
                    for (int c = 0; c < callSets.size(); c++) missingRow.add("./.");
                    matrix.add(missingRow);
                    continue;
                }
                int row = alleleIndexToRow.get(ai);
                List<String> rowData = new ArrayList<>(callSets.size());
                for (int c = 0; c < callSets.size(); c++) {
                    rowData.add(row < decoded.length && c < decoded[row].length
                            ? decoded[row][c]
                            : "./.");
                }
                matrix.add(rowData);
            }

            List<String> variantDbIds = snps.stream()
                    .map(s -> String.valueOf(s.getSnpFeatureId()))
                    .toList();
            List<String> callSetDbIds = callSets.stream()
                    .map(g -> String.valueOf(g.getStockSampleId()))
                    .toList();

            DataMatrix dm = new DataMatrix("GT", matrix, "GT", false);

            AlleleMatrixPagination pagination = new AlleleMatrixPagination(
                    req.effectiveVariantPage(),
                    req.effectiveVariantPageSize(),
                    snpTotal,
                    req.effectiveCallSetPage(),
                    req.effectiveCallSetPageSize(),
                    callSetTotal
            );

            return new AlleleMatrixDto(
                    callSetDbIds,
                    variantDbIds,
                    List.copyOf(req.variantSetDbIds()),
                    List.of(dm),
                    false,
                    "|",
                    "/",
                    "./.",
                    pagination
            );

        } catch (Exception e) {
            log.warn("fetchSubMatrix failed (HDF5 may not be configured): {}", e.getMessage());
            return emptyMatrix(req, snpTotal, callSetTotal);
        }
    }

    // -------------------------------------------------------------------------
    // Spec builders
    // -------------------------------------------------------------------------

    private Specification<SnpMetadata> buildSnpSpec(AlleleMatrixSearchRequest req, String datasetName) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("variantset"), datasetName));

            if (notEmpty(req.variantDbIds())) {
                List<Integer> ids = req.variantDbIds().stream()
                        .map(Integer::parseInt)
                        .toList();
                predicates.add(root.get("snpFeatureId").in(ids));
            }

            if (req.referenceName() != null && !req.referenceName().isBlank()) {
                try {
                    predicates.add(cb.equal(root.get("chromosome"), Integer.parseInt(req.referenceName())));
                } catch (NumberFormatException ignored) { }
            }

            if (req.start() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("position"), req.start().intValue()));
            }
            if (req.end() != null) {
                predicates.add(cb.lessThan(root.get("position"), req.end().intValue()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<Germplasm> buildCallSetSpec(AlleleMatrixSearchRequest req, String datasetName) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("dataset"), datasetName));

            if (notEmpty(req.callSetDbIds())) {
                List<Long> ids = req.callSetDbIds().stream()
                        .map(Long::parseLong)
                        .toList();
                predicates.add(root.get("stockSampleId").in(ids));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    // -------------------------------------------------------------------------
    // Helper — empty matrix response
    // -------------------------------------------------------------------------

    private AlleleMatrixDto emptyMatrix(AlleleMatrixSearchRequest req, long snpTotal, long callSetTotal) {
        AlleleMatrixPagination pagination = new AlleleMatrixPagination(
                req.effectiveVariantPage(),
                req.effectiveVariantPageSize(),
                snpTotal,
                req.effectiveCallSetPage(),
                req.effectiveCallSetPageSize(),
                callSetTotal
        );
        List<String> vsDbIds = req.variantSetDbIds() != null ? List.copyOf(req.variantSetDbIds()) : List.of();
        return new AlleleMatrixDto(
                List.of(), List.of(), vsDbIds,
                List.of(new DataMatrix("GT", List.of(), "GT", false)),
                false, "|", "/", "./.", pagination
        );
    }

    private static boolean notEmpty(List<?> list) {
        return list != null && !list.isEmpty();
    }
}
