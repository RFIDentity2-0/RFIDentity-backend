package com.rfidentity.service;

import com.rfidentity.api.dto.CurrentInventoryAssetDTO;
import com.rfidentity.model.CurrentInventoryAsset;
import com.rfidentity.repo.CurrentInventoryAssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final CurrentInventoryAssetRepository repository;

    @Override
    public Page<CurrentInventoryAssetDTO> getDashboardItems(Pageable pageable) {

        Page<CurrentInventoryAsset> page = repository.findAll(pageable);
        List<CurrentInventoryAssetDTO> listOfElementsInPage = page.getContent().stream()
                .map(this::toDTO)
                .collect(Collectors.toUnmodifiableList());

        return new PageImpl<>(listOfElementsInPage, pageable, page.getTotalElements());
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
