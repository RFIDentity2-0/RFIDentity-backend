package com.rfidentity.api.dto;

import lombok.Data;

@Data
public class DiffVmItemDTO {
    private String systemName;
    private String dnsName;
    private String type;
    private String manufacturer;
    private String hardwareType;
    private String serialNo;
    private String status;
    private String department;
}
