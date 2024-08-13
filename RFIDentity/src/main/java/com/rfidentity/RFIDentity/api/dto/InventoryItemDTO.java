package com.rfidentity.RFIDentity.api.dto;

import com.rfidentity.RFIDentity.model.Inventory;
import lombok.Data;

@Data
public class InventoryItemDTO {
    private long id;
    private String sapItemId;
    private String vmItemId;
    private InventoryDTO inventoryId;
}
