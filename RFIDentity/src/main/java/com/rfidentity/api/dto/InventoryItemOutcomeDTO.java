package com.rfidentity.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InventoryItemOutcomeDTO {

    private long id;
    private String location;
    private String comment;
    private String status;
    private String description;
    private String assetId;
    private LocalDate scannedDate;
    private long inventoryItemId;
}
