package com.rfidentity.service;

import com.rfidentity.api.dto.CurrentInventoryAssetsWithOutcomeDTO;
import com.rfidentity.api.dto.LocationAssetsSummaryDTO;
import com.rfidentity.repo.LocationAssetsSummaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationAssetsSummaryService {
    Page<LocationAssetsSummaryDTO> getLocationAssetsSummary(String location, Pageable pageable);

    Page<CurrentInventoryAssetsWithOutcomeDTO> getAssetsByLocation(String location, Pageable pageable);

    void updateComment(String assetId, String comment);
}
