package com.rfidentity.RFIDentity.service;

import com.rfidentity.RFIDentity.api.dto.InventoryDTO;
import com.rfidentity.RFIDentity.model.Inventory;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> getAllInventory();
    void addInventory(Inventory inventory);
}
