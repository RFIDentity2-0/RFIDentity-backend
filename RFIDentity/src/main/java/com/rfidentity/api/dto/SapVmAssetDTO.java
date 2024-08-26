package com.rfidentity.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class SapVmAssetDTO {
    private String assetId;
    private String description;
    private String sapRoom;
    private String systemName;
    private String dnsName;
    private String type;
    private String manufacturer;
    private String hardwareType;
    private String serialNo;
    private String status;
    private String department;
}
