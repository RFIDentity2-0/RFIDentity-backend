package com.rfidentity.service;

import com.rfidentity.api.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> getAllInventory();
}
