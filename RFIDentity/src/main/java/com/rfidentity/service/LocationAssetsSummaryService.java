package com.rfidentity.service;

import com.rfidentity.api.dto.CurrentInventoryAssetsWithOutcomeDTO;
import com.rfidentity.api.dto.LocationAssetsSummaryDTO;
import com.rfidentity.repo.LocationAssetsSummaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LocationAssetsSummaryService {
    Page<LocationAssetsSummaryDTO> getLocationAssetsSummary(List<String> locations, Pageable pageable);

    Page<CurrentInventoryAssetsWithOutcomeDTO> getAssetsByLocation(String location, Pageable pageable);

    void updateComment(String assetId, String comment);
}
