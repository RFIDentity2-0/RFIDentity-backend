package com.rfidentity.api.controller;

import com.rfidentity.api.dto.CurrentInventoryAssetsWithOutcomeDTO;
import com.rfidentity.api.dto.LocationAssetsSummaryDTO;
import com.rfidentity.api.dto.UpdateCommentDTO;
import com.rfidentity.service.LocationAssetsSummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LocationAssetsSummaryControllerImpl implements LocationAssetsSummaryController {

    private final LocationAssetsSummaryService locationAssetsSummaryService;

    @Override
    public ResponseEntity<Page<LocationAssetsSummaryDTO>> getLocationAssetsSummary(
            List<String> locations,
            Pageable pageable
    ) {
        Page<LocationAssetsSummaryDTO> result = locationAssetsSummaryService.getLocationAssetsSummary(locations, pageable);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Page<CurrentInventoryAssetsWithOutcomeDTO>> getAssetsByLocation(
            String location,
            Pageable pageable
    ) {
        Page<CurrentInventoryAssetsWithOutcomeDTO> assets = locationAssetsSummaryService.getAssetsByLocation(location, pageable);
        return ResponseEntity.ok(assets);
    }
    @Override
    public ResponseEntity<String> updateComment(
            String assetId,
            UpdateCommentDTO dto
    ) {
        locationAssetsSummaryService.updateComment(assetId, dto.getComment());
        return ResponseEntity.ok("Comment updated successfully for assetId: " + assetId);
    }
}
