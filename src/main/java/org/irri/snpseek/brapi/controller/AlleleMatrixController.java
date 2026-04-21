package org.irri.snpseek.brapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.irri.snpseek.brapi.dto.AlleleMatrixDto;
import org.irri.snpseek.brapi.dto.AlleleMatrixSearchRequest;
import org.irri.snpseek.brapi.dto.BrapiResponse;
import org.irri.snpseek.brapi.service.AlleleMatrixService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BrAPI v2.1 AlleleMatrix endpoints.
 *
 * <pre>
 * GET  /brapi/v2/allelematrix  → allele matrix (public)
 * </pre>
 */
@Tag(name = "Allele Matrix", description = "Retrieve genotype call matrices (BrAPI v2.1)")
@SecurityRequirements
@RestController
public class AlleleMatrixController {

    private final AlleleMatrixService alleleMatrixService;

    public AlleleMatrixController(AlleleMatrixService alleleMatrixService) {
        this.alleleMatrixService = alleleMatrixService;
    }

    // =========================================================================
    // GET /brapi/v2/allelematrix
    // =========================================================================

    @Operation(
        summary = "Get allele matrix",
        description = """
            Retrieve a paginated slice of the genotype matrix for a variant set.
            Results are organised in two independent dimensions: variants (rows) and
            call sets (columns).  Both dimensions support independent pagination via
            `dimensionVariantPage` / `dimensionCallSetPage` parameters.
            """
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Allele matrix returned",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrapiResponse.class)))
    })
    @GetMapping("/brapi/v2/allelematrix")
    public BrapiResponse<AlleleMatrixDto> getAlleleMatrix(
            @Parameter(description = "VariantSet ID to query")
            @RequestParam(required = false) String variantSetDbId,
            @Parameter(description = "Filter by specific variant IDs")
            @RequestParam(required = false) List<String> variantDbId,
            @Parameter(description = "Filter by specific call set IDs")
            @RequestParam(required = false) List<String> callSetDbId,
            @Parameter(description = "Reference / chromosome name")
            @RequestParam(required = false) String referenceName,
            @Parameter(description = "Start position (inclusive, 0-based)")
            @RequestParam(required = false) Long start,
            @Parameter(description = "End position (exclusive)")
            @RequestParam(required = false) Long end,
            @Parameter(description = "Variant page number (0-based)")
            @RequestParam(required = false) Integer dimensionVariantPage,
            @Parameter(description = "Variants per page")
            @RequestParam(required = false) Integer dimensionVariantPageSize,
            @Parameter(description = "CallSet page number (0-based)")
            @RequestParam(required = false) Integer dimensionCallSetPage,
            @Parameter(description = "CallSets per page")
            @RequestParam(required = false) Integer dimensionCallSetPageSize) {

        List<String> vsIds = variantSetDbId != null ? List.of(variantSetDbId) : List.of();

        AlleleMatrixSearchRequest req = new AlleleMatrixSearchRequest(
                vsIds,
                variantDbId,
                callSetDbId,
                referenceName,
                start,
                end,
                dimensionVariantPage,
                dimensionVariantPageSize,
                dimensionCallSetPage,
                dimensionCallSetPageSize
        );

        return BrapiResponse.of(alleleMatrixService.query(req));
    }
}
