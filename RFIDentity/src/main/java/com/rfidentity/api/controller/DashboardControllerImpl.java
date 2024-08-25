package com.rfidentity.api.controller;

import com.rfidentity.api.dto.CurrentInventoryAssetDTO;
import com.rfidentity.api.dto.CurrentLocationWithAssetsNumberDTO;
import com.rfidentity.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class DashboardControllerImpl implements DashboardController {

    private final DashboardService dashboardService;

    @Override
    public ResponseEntity<Page<CurrentInventoryAssetDTO>> getDashboardItems(
            final String assetId,
            final String description,
            final Pageable pageable
    ) {
        return  ResponseEntity.ok(dashboardService.getDashboardItems(assetId, description, pageable));
    }

    @Override
    public ResponseEntity<Page<CurrentLocationWithAssetsNumberDTO>> getLocationsWithAssetsNumber(
            String location,
            Pageable pageable
    ) {
        return ResponseEntity.ok(dashboardService.getLocationsWithAssetsNumber(location, pageable));
    }
}
