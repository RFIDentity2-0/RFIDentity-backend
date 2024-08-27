package com.rfidentity.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class MobileAssetDetailDTO {
    private String assetId;
    private String status;
    private String comment;
}
