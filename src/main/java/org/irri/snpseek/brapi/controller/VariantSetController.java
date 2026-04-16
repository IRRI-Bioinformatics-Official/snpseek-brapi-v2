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
import org.irri.snpseek.brapi.domain.SnpMetadata;
import org.irri.snpseek.brapi.domain.VariantSet;
import org.irri.snpseek.brapi.dto.BrapiListResponse;
import org.irri.snpseek.brapi.dto.BrapiResponse;
import org.irri.snpseek.brapi.dto.CallSetDto;
import org.irri.snpseek.brapi.dto.VariantSetDto;
import org.irri.snpseek.brapi.repository.GermplasmRepository;
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
    private final GenotypeStorageService storageService;

    public VariantSetController(VariantSetRepository variantSetRepository,
                                SnpMetadataRepository snpMetadataRepository,
                                GermplasmRepository germplasmRepository,
                                GenotypeStorageService storageService) {
        this.variantSetRepository  = variantSetRepository;
        this.snpMetadataRepository = snpMetadataRepository;
        this.germplasmRepository   = germplasmRepository;
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
            @Parameter(description = "0-based page number") @RequestParam(required = false) Integer page,
            @Parameter(description = "Number of results per page") @RequestParam(required = false) Integer pageSize) {

        int effectivePage     = page     != null ? page     : 0;
        int effectivePageSize = pageSize != null ? pageSize : 1000;

        Page<VariantSet> vsPage = variantSetRepository.findAll(
                PageRequest.of(effectivePage, effectivePageSize));

        List<VariantSetDto> dtos = vsPage.getContent().stream()
                .map(vs -> toDto(vs))
                .toList();

        return BrapiListResponse.of(dtos, vsPage.getNumber(), vsPage.getSize(), vsPage.getTotalElements());
    }

    // =========================================================================
    // GET /brapi/v2/variantsets/{variantSetDbId}
    // =========================================================================

    @Operation(
        summary = "Get a single variant set",
        description = "Return details for a single variant set, including variant and call set counts."
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
        return BrapiResponse.of(toDto(vs));
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

        VariantSet vs         = resolveVariantSet(variantSetDbId);
        String     datasetName = vs.getName();
        String     vsIdStr     = String.valueOf(vs.getVariantSetId());

        int effectivePage     = page     != null ? page     : 0;
        int effectivePageSize = pageSize != null ? pageSize : 1000;

        Specification<Germplasm> spec = (root, query, cb) ->
                cb.equal(root.get("dataset"), datasetName);

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

    private VariantSetDto toDto(VariantSet vs) {
        String name = vs.getName();
        long   variantSetDbId = vs.getVariantSetId().longValue();

        // Count SNPs and samples for this variant set
        Specification<SnpMetadata> snpSpec =
                (root, query, cb) -> cb.equal(root.get("variantset"), name);
        long variantCount = (name != null) ? snpMetadataRepository.count(snpSpec) : 0L;

        Specification<Germplasm> gsSpec =
                (root, query, cb) -> cb.equal(root.get("dataset"), name);
        long callSetCount = (name != null) ? germplasmRepository.count(gsSpec) : 0L;

        // Retrieve supported formats from storage service; fall back to empty on error
        String[] formats;
        try {
            formats = storageService.availableFormats(variantSetDbId);
        } catch (Exception e) {
            log.debug("availableFormats failed for variantSetDbId={}: {}", variantSetDbId, e.getMessage());
            formats = new String[0];
        }

        return VariantSetDto.from(vs, variantCount, callSetCount, formats);
    }
}
