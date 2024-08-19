package com.rfidentity.service;

import com.rfidentity.api.dto.InventoryDTO;
import com.rfidentity.model.Inventory;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> getAllInventory();
    void addInventory(Inventory inventory);
}
