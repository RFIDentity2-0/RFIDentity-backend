package com.rfidentity.api.controller;

import com.rfidentity.api.dto.CurrentInventoryAssetDTO;
import com.rfidentity.api.dto.LocationAssetsSummaryDTO;
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

@RequestMapping("/api/locations")
@Tag(name = "Locations", description = "Service for locations")
public interface LocationAssetsSummaryController {
    @GetMapping("/locationsToRoomsPage")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get locations with assets for rooms view",
            description = "Returns locations with assets from database."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LocationAssetsSummaryDTO.class)
                    )
            )
    }
    )
    ResponseEntity<Page<LocationAssetsSummaryDTO>> getLocationAssetsSummary(
            @RequestParam(value = "location", required = false) String location,
            @PageableDefault(size = 20) final Pageable pageable
    );
}
