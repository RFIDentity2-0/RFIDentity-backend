package com.rfidentity.api.controller;

import com.rfidentity.api.dto.MobileOutcomeUpdateDTO;
import com.rfidentity.api.dto.SapItemUpdateDTO;
import com.rfidentity.api.dto.UpdateCommentDTO;
import com.rfidentity.api.dto.VmItemUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/api/mobile")
@Tag(name = "Mobile", description = "Service for mobile app")
public interface MobileController {

    @PostMapping("/updateOutcome")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Update data for outcome assets",
            description = "Post data into the table."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Data update successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SapItemUpdateDTO.class)
                    )
            )
    }
    )
    ResponseEntity<Void> addAssetsOutcome(
            @RequestBody MobileOutcomeUpdateDTO mobileOutcomeUpdateDTO);
}
