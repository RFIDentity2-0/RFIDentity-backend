package com.rfidentity.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VmItemUpdateDTO {
    private String systemName;
    private String dnsName;
    private String type;
    private String manufacturer;
    private String hardwareType;
    private String serialNo;
    private String status;
    private String department;
    private String location;
    private String building;
    private String room;
}
