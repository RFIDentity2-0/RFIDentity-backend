package com.rfidentity.api.dto;

import lombok.Data;

@Data
public class InventoryItemDTO {
    private long id;
    private String sapItemId;
    private String vmItemId;
    private long inventoryId;
}
