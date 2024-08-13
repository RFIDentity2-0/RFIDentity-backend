package com.rfidentity.RFIDentity.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SapItemDTO {
    private long id;
    private long assetNo;
    private long subNo;
    private LocalDate capitalizedDate;
    private String description;
    private String room;
    private String assetId;
    private InventoryDTO inventoryId;
}
