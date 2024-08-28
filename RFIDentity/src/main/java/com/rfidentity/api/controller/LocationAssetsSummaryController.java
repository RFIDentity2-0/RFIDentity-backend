package com.rfidentity.api.controller;

import com.rfidentity.api.dto.CurrentInventoryAssetsWithOutcomeDTO;
import com.rfidentity.api.dto.LocationAssetsSummaryDTO;
import com.rfidentity.api.dto.UpdateCommentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/locations")
@Tag(name = "Locations", description = "Service for locations")
public interface LocationAssetsSummaryController {

    @GetMapping("/locationsToRoomsPage")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get locations with 8 assets for rooms view",
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
            @RequestParam(value = "location", required = false) List<String> locations,
            @SortDefault(sort = "location", direction = Sort.Direction.ASC)
            final Pageable pageable
    );

    @GetMapping("/insideLocation")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get assets data for specific location if location is null assets will go to default room",
            description = "Returns assets data for specific locations from database."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CurrentInventoryAssetsWithOutcomeDTO.class)
                    )
            )
    }
    )
    ResponseEntity<Page<CurrentInventoryAssetsWithOutcomeDTO>> getAssetsByLocation(
            @RequestParam (required = false) String location,
            @SortDefault(sort = "assetId", direction = Sort.Direction.ASC)
            @PageableDefault(size = 20) final Pageable pageable
    );

    @PutMapping("/comment")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Put comment for specific asset",
            description = "Update comment for specific asset from database."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdateCommentDTO.class)
                    )
            )
    }
    )
    ResponseEntity<String> updateComment(
            @RequestParam String assetId,
            @RequestBody UpdateCommentDTO dto
    );
}
