package com.rfidentity.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrentInventoryAssetsWithOutcomeDTO {
    private String assetId;
    private String description;
    private String inventoryStatus;
    private String comment;
}
