package org.irri.snpseek.brapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.criteria.Predicate;
import org.irri.snpseek.brapi.domain.Germplasm;
import org.irri.snpseek.brapi.domain.SnpMetadata;
import org.irri.snpseek.brapi.domain.VariantSet;
import org.irri.snpseek.brapi.dto.BrapiListResponse;
import org.irri.snpseek.brapi.dto.BrapiResponse;
import org.irri.snpseek.brapi.dto.CallSetDto;
import org.irri.snpseek.brapi.dto.VariantSetDto;
import org.irri.snpseek.brapi.repository.GermplasmRepository;
import org.irri.snpseek.brapi.repository.PlatformRepository;
import org.irri.snpseek.brapi.repository.SnpMetadataRepository;
import org.irri.snpseek.brapi.repository.VariantSetRepository;
import org.irri.snpseek.brapi.service.GenotypeStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * BrAPI v2.1 VariantSet endpoints.
 *
 * <pre>
 * GET  /brapi/v2/variantsets                             → paginated list (public)
 * GET  /brapi/v2/variantsets/{variantSetDbId}            → single VariantSet (public)
 * GET  /brapi/v2/variantsets/{variantSetDbId}/callsets   → paginated CallSets (public)
 * </pre>
 */
@Tag(name = "VariantSets", description = "Retrieve variant set metadata (BrAPI v2.1)")
@SecurityRequirements
@RestController
@RequestMapping("/brapi/v2/variantsets")
public class VariantSetController {

    private static final Logger log = LoggerFactory.getLogger(VariantSetController.class);

    private final VariantSetRepository   variantSetRepository;
    private final SnpMetadataRepository  snpMetadataRepository;
    private final GermplasmRepository    germplasmRepository;
    private final PlatformRepository     platformRepository;
    private final GenotypeStorageService storageService;

    public VariantSetController(VariantSetRepository variantSetRepository,
                                SnpMetadataRepository snpMetadataRepository,
                                GermplasmRepository germplasmRepository,
                                PlatformRepository platformRepository,
                                GenotypeStorageService storageService) {
        this.variantSetRepository  = variantSetRepository;
        this.snpMetadataRepository = snpMetadataRepository;
        this.germplasmRepository   = germplasmRepository;
        this.platformRepository    = platformRepository;
        this.storageService        = storageService;
    }

    // =========================================================================
    // GET /brapi/v2/variantsets
    // =========================================================================

    @Operation(
        summary = "List all variant sets",
        description = "Return a paginated list of all variant sets (genotyping arrays / chip panels)."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "VariantSet list returned",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiListResponse.class)))
    })
    @GetMapping
    public BrapiListResponse<VariantSetDto> listVariantSets(
            @Parameter(description = "Filter by variantSetDbId (variantset.variantset_id)")
            @RequestParam(required = false) String variantSetDbId,
            @Parameter(description = "Filter by referenceSetDbId (variantset.organism_id)")
            @RequestParam(required = false) String referenceSetDbId,
            @Parameter(description = "Return only the variant set that contains this variantDbId")
            @RequestParam(required = false) String variantDbId,
            @Parameter(description = "Return only the variant set that contains this callSetDbId")
            @RequestParam(required = false) String callSetDbId,
            @Parameter(description = "0-based page number") @RequestParam(required = false) Integer page,
            @Parameter(description = "Number of results per page") @RequestParam(required = false) Integer pageSize) {

        int effectivePage     = page     != null ? page     : 0;
        int effectivePageSize = pageSize != null ? pageSize : 1000;

        Specification<VariantSet> spec = buildSpec(variantSetDbId, referenceSetDbId, variantDbId, callSetDbId);

        Page<VariantSet> vsPage = variantSetRepository.findAll(
                spec, PageRequest.of(effectivePage, effectivePageSize));

        List<VariantSetDto> dtos = vsPage.getContent().stream()
                .map(vs -> toDtoSummary(vs))
                .toList();

        return BrapiListResponse.of(dtos, vsPage.getNumber(), vsPage.getSize(), vsPage.getTotalElements());
    }

    /**
     * Build a JPA {@link Specification} for the {@code variantset} table from
     * the BrAPI query parameters supported by this server.
     *
     * <ul>
     *   <li>{@code variantSetDbId}  → {@code variantset.variantset_id = ?}</li>
     *   <li>{@code referenceSetDbId} → {@code variantset.organism_id = ?}
     *       (organism acts as the reference-set / genome assembly in this schema)</li>
     *   <li>{@code variantDbId}     → resolve {@code variantset} name from
     *       {@code v_snp_refposindex_v2} then filter {@code variantset.name = ?}</li>
     *   <li>{@code callSetDbId}     → resolve {@code dataset} name from
     *       {@code v_allstock_basicprop} then filter {@code variantset.name = ?}</li>
     * </ul>
     */
    private Specification<VariantSet> buildSpec(String variantSetDbId,
                                                String referenceSetDbId,
                                                String variantDbId,
                                                String callSetDbId) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // variantset.variantset_id = ?
            if (variantSetDbId != null && !variantSetDbId.isBlank()) {
                try {
                    predicates.add(cb.equal(root.get("variantSetId"), Integer.parseInt(variantSetDbId)));
                } catch (NumberFormatException ignored) {
                    predicates.add(cb.disjunction());
                }
            }

            // variantset.organism_id = ? (organism_id doubles as referenceSetDbId)
            if (referenceSetDbId != null && !referenceSetDbId.isBlank()) {
                try {
                    predicates.add(cb.equal(root.get("organismId"), Integer.parseInt(referenceSetDbId)));
                } catch (NumberFormatException ignored) {
                    predicates.add(cb.disjunction());
                }
            }

            // Resolve variantDbId → variantset name via v_snp_refposindex_v2
            if (variantDbId != null && !variantDbId.isBlank()) {
                try {
                    snpMetadataRepository.findById(Integer.parseInt(variantDbId))
                            .map(SnpMetadata::getVariantset)
                            .ifPresentOrElse(
                                    name -> predicates.add(cb.equal(root.get("name"), name)),
                                    ()   -> predicates.add(cb.disjunction())
                            );
                } catch (NumberFormatException ignored) {
                    predicates.add(cb.disjunction());
                }
            }

            // Resolve callSetDbId (stock_sample_id) → dataset name via v_allstock_basicprop
            if (callSetDbId != null && !callSetDbId.isBlank()) {
                try {
                    germplasmRepository.findById(Integer.parseInt(callSetDbId))
                            .map(Germplasm::getDataset)
                            .ifPresentOrElse(
                                    name -> predicates.add(cb.equal(root.get("name"), name)),
                                    ()   -> predicates.add(cb.disjunction())
                            );
                } catch (NumberFormatException ignored) {
                    predicates.add(cb.disjunction());
                }
            }

            return predicates.isEmpty()
                    ? cb.conjunction()
                    : cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    // =========================================================================
    // GET /brapi/v2/variantsets/{variantSetDbId}
    // =========================================================================

    @Operation(
        summary = "Get a single variant set",
        description = "Return details for a single variant set."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "VariantSet found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiResponse.class))),
        @ApiResponse(responseCode = "404", description = "VariantSet not found")
    })
    @GetMapping("/{variantSetDbId}")
    public BrapiResponse<VariantSetDto> getVariantSet(
            @Parameter(description = "Numeric variantSetDbId", required = true)
            @PathVariable String variantSetDbId) {
        VariantSet vs = resolveVariantSet(variantSetDbId);
        return BrapiResponse.of(toDtoSummary(vs));
    }

    // =========================================================================
    // GET /brapi/v2/variantsets/{variantSetDbId}/callsets
    // =========================================================================

    @Operation(
        summary = "List call sets in a variant set",
        description = "Return a paginated list of call sets (samples) belonging to the given variant set."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "CallSet list returned",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiListResponse.class))),
        @ApiResponse(responseCode = "404", description = "VariantSet not found")
    })
    @GetMapping("/{variantSetDbId}/callsets")
    public BrapiListResponse<CallSetDto> listCallSets(
            @Parameter(description = "Numeric variantSetDbId", required = true)
            @PathVariable String variantSetDbId,
            @Parameter(description = "0-based page number") @RequestParam(required = false) Integer page,
            @Parameter(description = "Number of results per page") @RequestParam(required = false) Integer pageSize) {

        VariantSet vs     = resolveVariantSet(variantSetDbId);
        Integer    vsId   = vs.getVariantSetId();
        String     vsIdStr = String.valueOf(vsId);

        List<String> datasetNames = platformRepository.findDatasetNamesByVariantSetId(vsId);
        if (datasetNames.isEmpty()) {
            return BrapiListResponse.of(List.of(), 0, pageSize != null ? pageSize : 1000, 0L);
        }

        int effectivePage     = page     != null ? page     : 0;
        int effectivePageSize = pageSize != null ? pageSize : 1000;

        Specification<Germplasm> spec = (root, query, cb) ->
                root.get("dataset").in(datasetNames);

        Page<Germplasm> germplasmPage = germplasmRepository.findAll(
                spec, PageRequest.of(effectivePage, effectivePageSize, Sort.by("stockSampleId")));

        List<CallSetDto> dtos = germplasmPage.getContent().stream()
                .map(g -> CallSetDto.from(g, List.of(vsIdStr)))
                .toList();

        return BrapiListResponse.of(dtos,
                germplasmPage.getNumber(),
                germplasmPage.getSize(),
                germplasmPage.getTotalElements());
    }

    // =========================================================================
    // Helpers
    // =========================================================================

    private VariantSet resolveVariantSet(String variantSetDbId) {
        int id;
        try {
            id = Integer.parseInt(variantSetDbId);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "VariantSet not found for variantSetDbId: " + variantSetDbId);
        }
        return variantSetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "VariantSet not found for variantSetDbId: " + variantSetDbId));
    }

    /**
     * Builds a DTO from the {@code variantset} table only — no COUNT queries against views.
     */
    private VariantSetDto toDtoSummary(VariantSet vs) {
        Integer variantSetDbId = vs.getVariantSetId();
        String[] formats;
        try {
            formats = storageService.availableFormats(variantSetDbId);
        } catch (Exception e) {
            log.debug("availableFormats failed for variantSetDbId={}: {}", variantSetDbId, e.getMessage());
            formats = new String[0];
        }
        return VariantSetDto.from(vs, null, null, formats);
    }

}
