package com.rfidentity.api.controller;

import com.rfidentity.api.dto.CurrentInventoryAssetDTO;
import com.rfidentity.api.dto.CurrentLocationWithAssetsNumberDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard", description = "Service for dashboard")
public interface DashboardController {

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get page of assets for dashboard.",
            description = "Returns page of assets from database."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CurrentInventoryAssetDTO.class)
                    )
            )
    }
    )
    ResponseEntity<Page<CurrentInventoryAssetDTO>> getDashboardItems(
            @RequestParam(required = false) String assetId,
            @RequestParam(required = false) String description,
            @SortDefault(sort = "status,desc") @PageableDefault(size = 20) final Pageable pageable
    );


    @GetMapping("/listLocationsWithAssets")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get page of locations with assets number.",
            description = "Return page of locations with assets number and date of scan."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CurrentLocationWithAssetsNumberDTO.class)
                    )
            )
    }
    )
    ResponseEntity<Page<CurrentLocationWithAssetsNumberDTO>> getLocationsWithAssetsNumber(
            @RequestParam(required = false) String location,
            @SortDefault(sort = "status,desc") @PageableDefault(size = 20) final Pageable pageable
    );

}
