package com.rfidentity.service;

import com.rfidentity.api.dto.CurrentInventoryAssetDTO;
import com.rfidentity.api.dto.CurrentLocationWithAssetsNumberDTO;
import com.rfidentity.model.CurrentInventoryAsset;
import com.rfidentity.model.CurrentLocationsWithAssetsNumber;
import com.rfidentity.repo.CurrentInventoryAssetRepository;
import com.rfidentity.repo.CurrentInventoryAssetSpecification;
import com.rfidentity.repo.CurrentLocationWithAssetsNumberRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final CurrentInventoryAssetRepository currentInventoryAssetRepository;
    private final CurrentLocationWithAssetsNumberRepository currentLocationWithAssetsNumberRepository;

    @Override
    public Page<CurrentInventoryAssetDTO> getDashboardItems(
            final String assetId,
            final String description,
            Pageable pageable
    ) {

        Specification<CurrentInventoryAsset> specification = CurrentInventoryAssetSpecification.assetSpecification(
                assetId,
                description
        );

        Page<CurrentInventoryAsset> page = currentInventoryAssetRepository.findAll(specification, pageable);
        List<CurrentInventoryAssetDTO> listOfElementsInPage = page.getContent().stream()
                .map(this::toDTO)
                .toList();

        return new PageImpl<>(listOfElementsInPage, pageable, page.getTotalElements());
    }

    @Override
    public Page<CurrentLocationWithAssetsNumberDTO> getLocationsWithAssetsNumber(
            final String location,
            final Pageable pageable
    ) {

        Page<CurrentLocationsWithAssetsNumber> page = !StringUtils.isBlank(location)
                        ? currentLocationWithAssetsNumberRepository.findByLocationLikeName(location, pageable)
                        : currentLocationWithAssetsNumberRepository.findAll(pageable);

        List<CurrentLocationWithAssetsNumberDTO> list = page.getContent().stream()
                .map(this::toDTO)
                .toList();
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    private CurrentLocationWithAssetsNumberDTO toDTO(CurrentLocationsWithAssetsNumber currentLocation) {
        return CurrentLocationWithAssetsNumberDTO.builder()
                .withLocation(currentLocation.getLocation())
                .withScannedDate(currentLocation.getScannedDate())
                .withCount(currentLocation.getCount())
                .build();
    }

    private CurrentInventoryAssetDTO toDTO(CurrentInventoryAsset asset) {
            return CurrentInventoryAssetDTO.builder()
                    .withInventoryId(asset.getInventoryId())
                    .withAssetId(asset.getAssetId())
                    .withDescription(asset.getDescription())
                    .withSapRoom(asset.getSapRoom())
                    .withStatus(asset.getStatus())
                    .withLocation(asset.getLocation())
                    .withBuilding(asset.getBuilding())
                    .withRoom(asset.getRoom())
                    .withItemStatus(asset.getItemStatus())
                    .build();
    }
}
