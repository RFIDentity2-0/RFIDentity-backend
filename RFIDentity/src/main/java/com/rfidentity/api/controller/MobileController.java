package com.rfidentity.api.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

//@RequestMapping("/api/mobile")
//@Tag(name = "Mobile", description = "Service for mobile app")
//public interface MobileController {
//
//    @PutMapping("/updateStatus/{assetId}")
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(
//            summary = "Update data of specific vm assets for action page.",
//            description = "Put data of specific vm assets to database."
//    )
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "204",
//                    description = "Data update successfully",
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema = @Schema(implementation = UpdateCommentDTO.class)
//                    )
//            )
//    }
//    )
//    ResponseEntity<String> updateItemStatus(
//            @PathVariable String assetId,
//            @RequestBody UpdateCommentDTO dto);
//}
