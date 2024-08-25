package com.rfidentity.api.controller;

import com.rfidentity.api.dto.CurrentInventoryAssetDTO;
import com.rfidentity.api.dto.SapItemUpdateDTO;
import com.rfidentity.api.dto.SapVmAssetDTO;
import com.rfidentity.api.dto.VmItemUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/assets")
@Tag(name = "SapAndVmAssets", description = "Service for actions")
public interface SapVmAssetController {

    @GetMapping("/{assetId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get data of specific assets for action page.",
            description = "Returns data of specific assets from database."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SapVmAssetDTO.class)
                    )
            )
    }
    )
    ResponseEntity<SapVmAssetDTO> getAssetById(@PathVariable String assetId);


    @PutMapping("/{inventoryId}/sap/{assetId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update data of specific sap assets for action page.",
            description = "Put data of specific sap assets to database."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Data update successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SapItemUpdateDTO.class)
                    )
            )
    }
    )
    ResponseEntity<Void> updateSapItem(@PathVariable Long inventoryId, @PathVariable String assetId, @RequestBody SapItemUpdateDTO dto);

    @PutMapping("/{inventoryId}/vm/{assetId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update data of specific vm assets for action page.",
            description = "Put data of specific vm assets to database."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Data update successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SapItemUpdateDTO.class)
                    )
            )
    }
    )
    ResponseEntity<Void> updateVmItem(@PathVariable Long inventoryId, @PathVariable String assetId, @RequestBody VmItemUpdateDTO dto);
}
