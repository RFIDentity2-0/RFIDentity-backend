package com.rfidentity.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class LocationAssetsSummaryDTO {
    private String location;
    private Long assetCount;
    private List<AssetDetailDTO> assets;
}
