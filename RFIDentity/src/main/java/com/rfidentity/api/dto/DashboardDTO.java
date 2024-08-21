package com.rfidentity.api.dto;

import lombok.Data;

@Data
public class DashboardDTO {
    private String assetId;
    private String description;
    private String vmLocation;
    private String sapRoom;
    private String status;
}
