package org.irri.snpseek.brapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.irri.snpseek.brapi.domain.Germplasm;
import org.irri.snpseek.brapi.dto.BrapiListResponse;
import org.irri.snpseek.brapi.dto.BrapiResponse;
import org.irri.snpseek.brapi.dto.CallSetDto;
import org.irri.snpseek.brapi.repository.GermplasmRepository;
import org.irri.snpseek.brapi.repository.PlatformRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * BrAPI v2.1 CallSet endpoints.
 *
 * <pre>
 * GET  /brapi/v2/callsets                → paginated list (public)
 * GET  /brapi/v2/callsets/{callSetDbId}  → single CallSet (public)
 * </pre>
 *
 * <p>A CallSet in SNPseek terms maps to a row in {@code v_allstock_basicprop}
 * where {@code stock_sample_id} is the HDF5 column index (callSetDbId) and
 * {@code stock_id} is the germplasmDbId.
 */
@Tag(name = "CallSets", description = "Retrieve genotyping call sets (BrAPI v2.1)")
@SecurityRequirements
@RestController
@RequestMapping("/brapi/v2/callsets")
public class CallSetController {

    private final GermplasmRepository  germplasmRepository;
    private final PlatformRepository   platformRepository;

    public CallSetController(GermplasmRepository germplasmRepository,
                             PlatformRepository platformRepository) {
        this.germplasmRepository = germplasmRepository;
        this.platformRepository  = platformRepository;
    }

    // =========================================================================
    // GET /brapi/v2/callsets
    // =========================================================================

    @Operation(
        summary = "List call sets",
        description = "Return a paginated list of call sets filtered by variantSetDbId, germplasmDbId, or callSetDbId."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "CallSet list returned",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiListResponse.class)))
    })
    @GetMapping
    public BrapiListResponse<CallSetDto> listCallSets(
            @Parameter(description = "Filter by variantSetDbId (resolves to dataset names via platform → db)")
            @RequestParam(required = false) String variantSetDbId,
            @Parameter(description = "Filter by germplasmDbId (numeric stockId)")
            @RequestParam(required = false) String germplasmDbId,
            @Parameter(description = "Filter by callSetDbId (numeric stockSampleId)")
            @RequestParam(required = false) String callSetDbId,
            @Parameter(description = "0-based page number")
            @RequestParam(required = false) Integer page,
            @Parameter(description = "Number of results per page")
            @RequestParam(required = false) Integer pageSize) {

        int effectivePage     = page     != null ? page     : 0;
        int effectivePageSize = pageSize != null ? pageSize : 1000;

        final List<String> resolvedDatasets = resolveDatasetNames(variantSetDbId);

        Specification<Germplasm> spec = buildSpec(resolvedDatasets, germplasmDbId, callSetDbId);

        Page<Germplasm> germplasmPage = germplasmRepository.findAll(
                spec, PageRequest.of(effectivePage, effectivePageSize, Sort.by("stockSampleId")));

        // Pre-compute dataset → variantSetIds to avoid N+1 inside the stream
        List<String> knownDatasets = !resolvedDatasets.isEmpty()
                ? resolvedDatasets
                : germplasmPage.getContent().stream()
                        .map(Germplasm::getDataset)
                        .filter(d -> d != null)
                        .distinct()
                        .toList();

        java.util.Map<String, List<String>> datasetToVsIds = knownDatasets.stream()
                .collect(java.util.stream.Collectors.toMap(
                        name -> name,
                        this::buildVariantSetIdsForDataset));

        List<CallSetDto> dtos = germplasmPage.getContent().stream()
                .map(g -> CallSetDto.from(g,
                        datasetToVsIds.getOrDefault(g.getDataset(), List.of())))
                .toList();

        return BrapiListResponse.of(dtos,
                germplasmPage.getNumber(),
                germplasmPage.getSize(),
                germplasmPage.getTotalElements());
    }

    // =========================================================================
    // GET /brapi/v2/callsets/{callSetDbId}
    // =========================================================================

    @Operation(
        summary = "Get a single call set",
        description = "Return details for a single call set identified by its callSetDbId (stock_sample_id)."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "CallSet found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiResponse.class))),
        @ApiResponse(responseCode = "404", description = "CallSet not found")
    })
    @GetMapping("/{callSetDbId}")
    public BrapiResponse<CallSetDto> getCallSet(
            @Parameter(description = "Numeric callSetDbId (stock_sample_id)", required = true)
            @PathVariable String callSetDbId) {
        int stockSampleId;
        try {
            stockSampleId = Integer.parseInt(callSetDbId);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "CallSet not found for callSetDbId: " + callSetDbId);
        }
        Germplasm g = germplasmRepository.findByStockSampleId(stockSampleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "CallSet not found for callSetDbId: " + callSetDbId));

        List<String> variantSetIds = buildVariantSetIdsForDataset(g.getDataset());
        return BrapiResponse.of(CallSetDto.from(g, variantSetIds));
    }

    // =========================================================================
    // Helpers
    // =========================================================================

    /** Resolve variantSetDbId → dataset names via platform → db join. */
    private List<String> resolveDatasetNames(String variantSetDbId) {
        if (variantSetDbId == null || variantSetDbId.isBlank()) return List.of();
        try {
            return platformRepository.findDatasetNamesByVariantSetId(Integer.parseInt(variantSetDbId));
        } catch (NumberFormatException e) {
            return List.of();
        }
    }

    /** Reverse lookup: dataset name → variantSetDbId strings via db → platform join. */
    private List<String> buildVariantSetIdsForDataset(String datasetName) {
        if (datasetName == null) return List.of();
        return platformRepository.findVariantSetIdsByDatasetName(datasetName)
                .stream()
                .map(String::valueOf)
                .toList();
    }

    private Specification<Germplasm> buildSpec(List<String> datasetNames, String germplasmDbId, String callSetDbId) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!datasetNames.isEmpty()) {
                predicates.add(root.get("dataset").in(datasetNames));
            }
            if (germplasmDbId != null && !germplasmDbId.isBlank()) {
                try {
                    predicates.add(cb.equal(root.get("stockId"), Integer.parseInt(germplasmDbId)));
                } catch (NumberFormatException ignored) {
                    predicates.add(cb.disjunction());
                }
            }
            if (callSetDbId != null && !callSetDbId.isBlank()) {
                try {
                    predicates.add(cb.equal(root.get("stockSampleId"), Integer.parseInt(callSetDbId)));
                } catch (NumberFormatException ignored) {
                    predicates.add(cb.disjunction());
                }
            }

            return predicates.isEmpty()
                    ? cb.conjunction()
                    : cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
