package com.rfidentity.api.controller;

import com.rfidentity.api.dto.LocationAssetsSummaryDTO;
import com.rfidentity.service.LocationAssetsSummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LocationAssetsSummaryControllerImpl implements LocationAssetsSummaryController {

    private final LocationAssetsSummaryService locationAssetsSummaryService;

    @Override
    public ResponseEntity<Page<LocationAssetsSummaryDTO>> getLocationAssetsSummary(
            String location,
            Pageable pageable
    ) {
        Page<LocationAssetsSummaryDTO> page = locationAssetsSummaryService.getLocationAssetsSummary(location, pageable);
        return ResponseEntity.ok(page);
    }
}
