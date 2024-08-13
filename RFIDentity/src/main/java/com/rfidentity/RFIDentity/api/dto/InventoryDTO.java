package com.rfidentity.RFIDentity.api.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InventoryDTO {
    private long id;
    private LocalDate date;
    private List<SapItemDTO> sapItems;
    private List<VmItemDTO> vmItems;
    private List<InventoryItemDTO> inventoryItems;
}
