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
import org.irri.snpseek.brapi.repository.VariantSetRepository;
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
    private final VariantSetRepository variantSetRepository;

    public CallSetController(GermplasmRepository germplasmRepository,
                             VariantSetRepository variantSetRepository) {
        this.germplasmRepository  = germplasmRepository;
        this.variantSetRepository = variantSetRepository;
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
            @Parameter(description = "Filter by variantSetDbId (resolves to dataset name)")
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

        // Resolve variantSetDbId → dataset name (used to filter by dataset column)
        final String resolvedDataset = resolveDatasetName(variantSetDbId);

        Specification<Germplasm> spec = buildSpec(resolvedDataset, germplasmDbId, callSetDbId);

        Page<Germplasm> germplasmPage = germplasmRepository.findAll(
                spec, PageRequest.of(effectivePage, effectivePageSize, Sort.by("stockSampleId")));

        // Build variantSetIds to attach to each CallSet DTO
        List<String> variantSetIds = resolvedDataset != null
                ? buildVariantSetIdsForDataset(resolvedDataset)
                : List.of();

        List<CallSetDto> dtos = germplasmPage.getContent().stream()
                .map(g -> {
                    List<String> vsIds = !variantSetIds.isEmpty()
                            ? variantSetIds
                            : buildVariantSetIdsForDataset(g.getDataset());
                    return CallSetDto.from(g, vsIds);
                })
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
        Germplasm g = germplasmRepository.findById(stockSampleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "CallSet not found for callSetDbId: " + callSetDbId));

        List<String> variantSetIds = buildVariantSetIdsForDataset(g.getDataset());
        return BrapiResponse.of(CallSetDto.from(g, variantSetIds));
    }

    // =========================================================================
    // Helpers
    // =========================================================================

    /** Resolve a variantSetDbId string to the corresponding {@code VariantSet.name}. */
    private String resolveDatasetName(String variantSetDbId) {
        if (variantSetDbId == null || variantSetDbId.isBlank()) return null;
        try {
            return variantSetRepository.findById(Integer.parseInt(variantSetDbId))
                    .map(vs -> vs.getName())
                    .orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Return the variantSetDbId strings for all VariantSets whose name matches
     * the given dataset name.
     */
    private List<String> buildVariantSetIdsForDataset(String datasetName) {
        if (datasetName == null) return List.of();
        return variantSetRepository.findAll().stream()
                .filter(vs -> datasetName.equals(vs.getName()))
                .map(vs -> String.valueOf(vs.getVariantSetId()))
                .toList();
    }

    private Specification<Germplasm> buildSpec(String datasetName, String germplasmDbId, String callSetDbId) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (datasetName != null) {
                predicates.add(cb.equal(root.get("dataset"), datasetName));
            }
            if (germplasmDbId != null && !germplasmDbId.isBlank()) {
                try {
                    predicates.add(cb.equal(root.get("stockId"), Long.parseLong(germplasmDbId)));
                } catch (NumberFormatException ignored) {
                    predicates.add(cb.disjunction()); // no match
                }
            }
            if (callSetDbId != null && !callSetDbId.isBlank()) {
                try {
                    predicates.add(cb.equal(root.get("stockSampleId"), Integer.parseInt(callSetDbId)));
                } catch (NumberFormatException ignored) {
                    predicates.add(cb.disjunction()); // no match
                }
            }

            return predicates.isEmpty()
                    ? cb.conjunction()
                    : cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
