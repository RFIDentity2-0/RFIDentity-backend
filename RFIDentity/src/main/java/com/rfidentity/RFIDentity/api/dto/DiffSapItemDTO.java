package com.rfidentity.RFIDentity.api.dto;

import lombok.Data;

@Data
public class DiffSapItemDTO {
    private Long id;
    private String description;
    private String room;
    private String assetId;
    private Long inventoryId;
}
