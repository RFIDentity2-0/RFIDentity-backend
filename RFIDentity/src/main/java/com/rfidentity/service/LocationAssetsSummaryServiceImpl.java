package com.rfidentity.service;

import com.rfidentity.api.dto.AssetDetailDTO;
import com.rfidentity.api.dto.CurrentInventoryAssetsWithOutcomeDTO;
import com.rfidentity.api.dto.LocationAssetsSummaryDTO;
import com.rfidentity.model.CurrentInventoryAsset;
import com.rfidentity.model.CurrentInventoryAssetsWithOutcome;
import com.rfidentity.model.CurrentLocationsWithAssetsNumber;
import com.rfidentity.model.InventoryAssetsOutcome;
import com.rfidentity.repo.CurrentInventoryAssetSpecification;
import com.rfidentity.repo.CurrentLocationSpecification;
import com.rfidentity.repo.InventoryAssetOutcomeRepository;
import com.rfidentity.model.LocationAssetsSummary;
import com.rfidentity.repo.CurrentInventoryAssetsWithOutcomeRepository;
import com.rfidentity.repo.LocationAssetsSummaryRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LocationAssetsSummaryServiceImpl implements LocationAssetsSummaryService {

    private final LocationAssetsSummaryRepository locationAssetsSummaryRepository;

    private final CurrentInventoryAssetsWithOutcomeRepository outcomeRepository;

    private final InventoryAssetOutcomeRepository inventoryAssetsOutcomeRepository;


    @Override
    public Page<LocationAssetsSummaryDTO> getLocationAssetsSummary(List<String> locations, Pageable pageable) {
        List<LocationAssetsSummary> assets;

        if (locations != null && !locations.isEmpty()) {
            assets = locationAssetsSummaryRepository.findByLocationIn(locations);
        } else {
            assets = locationAssetsSummaryRepository.findAll();
        }

        Map<String, List<LocationAssetsSummary>> groupedByLocation = assets.stream()
                .filter(asset -> asset != null)
                .collect(Collectors.groupingBy(asset -> {
                    String location = asset.getLocation();
                    return (location == null || location.isEmpty()) ? "Default Room" : location;
                }));

        List<LocationAssetsSummaryDTO> locationList = groupedByLocation.entrySet().stream()
                .map(entry -> {
                    String loc = entry.getKey();
                    List<AssetDetailDTO> assetDTOs = entry.getValue().stream()
                            .map(asset -> AssetDetailDTO.builder()
                                    .withAssetId(asset.getAssetId())
                                    .withDescription(asset.getDescription())
                                    .withItemStatus(asset.getItemStatus())
                                    .build())
                            .collect(Collectors.toList());
                    return LocationAssetsSummaryDTO.builder()
                            .withLocation(loc)
                            .withAssetCount(assets.getFirst().getAssetCount())
                            .withAssets(assetDTOs)
                            .build();
                })
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), locationList.size());
        List<LocationAssetsSummaryDTO> paginatedList = locationList.subList(start, end);

        return new PageImpl<>(paginatedList, pageable, locationList.size());
    }


    @Override
    public Page<CurrentInventoryAssetsWithOutcomeDTO> getAssetsByLocation(String location, Pageable pageable) {
        Page<CurrentInventoryAssetsWithOutcome> assetsPage = outcomeRepository.findByLocation(location, pageable);

        List<CurrentInventoryAssetsWithOutcomeDTO> dtos = assetsPage.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, assetsPage.getTotalElements());
    }

    private CurrentInventoryAssetsWithOutcomeDTO toDTO(CurrentInventoryAssetsWithOutcome asset) {
        return CurrentInventoryAssetsWithOutcomeDTO.builder()
                .withAssetId(asset.getAssetId())
                .withDescription(asset.getDescription())
                .withItemStatus(asset.getItemStatus())
                .withComment(asset.getComment())
                .build();
    }

    @Override
    public void updateComment(String assetId, String comment) {
        Long latestInventoryId = inventoryAssetsOutcomeRepository.findLatestInventoryIdForAsset(assetId)
                .orElseThrow(() -> new EntityNotFoundException("No inventory found for assetId: " + assetId));

        InventoryAssetsOutcome outcome = inventoryAssetsOutcomeRepository.findByInventoryIdAndAssetId(latestInventoryId, assetId)
                .orElseThrow(() -> new EntityNotFoundException("No record found for inventoryId: " + latestInventoryId + " and assetId: " + assetId));

        outcome.setComment(comment);

        inventoryAssetsOutcomeRepository.save(outcome);
    }
}
