package com.rfidentity.RFIDentity.api.dto;

import lombok.Data;

@Data
public class DashboardDTO {
    private String assetId;
    private String description;
    private String vmLocation;
    private String sapRoom;
    private String status;
}
