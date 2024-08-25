package com.rfidentity.api.controller;


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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

//@RequestMapping("/api/rooms")
//@Tag(name = "Rooms", description = "Service for rooms")
//public interface RoomSummaryController {
//
//    @GetMapping("/summary")
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(
//            summary = "Get room summary for the latest inventory.",
//            description = "Returns a summary of rooms including asset counts and details."
//    )
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "OK",
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema = @Schema(implementation = RoomSummaryDTO.class)
//                    )
//            )
//    })
//    ResponseEntity<Page<RoomSummaryDTO>> getRoomSummary(Pageable pageable);
//}
