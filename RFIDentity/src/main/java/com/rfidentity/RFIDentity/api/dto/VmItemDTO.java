package com.rfidentity.RFIDentity.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VmItemDTO {

    private long id;
    private String systemName;
    private String dnsName;
    private String type;
    private String manufacturer;
    private String hardwareType;
    private String serialNo;
    private LocalDate dateOfInstallation;
    private String status;
    private String department;
    private String personId;
    private String lastName;
    private String firstName;
    private String location;
    private String building;
    private String room;
    private String assetId;
    private long inventoryId;
}
