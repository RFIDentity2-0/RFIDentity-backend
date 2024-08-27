package com.rfidentity.service;

import com.rfidentity.api.dto.CurrentInventoryAssetDTO;
import com.rfidentity.api.dto.CurrentLocationWithAssetsNumberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface DashboardService {

    Page<CurrentInventoryAssetDTO> getDashboardItems(
            final String assetId,
            final String description,
            Pageable pageable
    );

    Page<CurrentLocationWithAssetsNumberDTO> getLocationsWithAssetsNumber(
            final String locationName,
            final Pageable pageable
    );

}
