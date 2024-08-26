package com.rfidentity.service;

import com.rfidentity.api.dto.AssetDetailDTO;
import com.rfidentity.api.dto.LocationAssetsSummaryDTO;
import com.rfidentity.model.LocationAssetsSummary;
import com.rfidentity.repo.LocationAssetsSummaryRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LocationAssetsSummaryServiceImpl implements LocationAssetsSummaryService {

    private final LocationAssetsSummaryRepository locationAssetsSummaryRepository;

    @Override
    public Page<LocationAssetsSummaryDTO> getLocationAssetsSummary(String location, Pageable pageable) {
        Page<LocationAssetsSummary> page = !StringUtils.isBlank(location)
                ? locationAssetsSummaryRepository.findByLocationLikeName(location, pageable)
                : locationAssetsSummaryRepository.findAll(pageable);

        Map<String, List<LocationAssetsSummary>> groupedByLocation = page.getContent().stream()
                .collect(Collectors.groupingBy(LocationAssetsSummary::getLocation));

        List<LocationAssetsSummaryDTO> list = groupedByLocation.entrySet().stream()
                .map(entry -> {
                    String loc = entry.getKey();
                    List<AssetDetailDTO> assets = entry.getValue().stream()
                            .map(asset -> AssetDetailDTO.builder()
                                    .withAssetId(asset.getAssetId())
                                    .withDescription(asset.getDescription())
                                    .withStatus(asset.getStatus())
                                    .build())
                            .collect(Collectors.toList());
                    return LocationAssetsSummaryDTO.builder()
                            .withLocation(loc)
                            .withAssetCount((long) assets.size())
                            .withAssets(assets)
                            .build();
                })
                .collect(Collectors.toList());

        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

}
