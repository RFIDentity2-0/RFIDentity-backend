package com.rfidentity.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequiredArgsConstructor
//@Slf4j
//public class RoomSummaryControllerImpl {
//
//
//    private final RoomSummaryService roomSummaryService;
//
//    public ResponseEntity<Page<RoomSummaryDTO>> getRoomSummary(
//            @PageableDefault(size = 20) Pageable pageable) {
//        return ResponseEntity.ok(roomSummaryService.getRoomSummary(pageable));
//    }
//}
