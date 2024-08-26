package com.rfidentity.service;

import com.rfidentity.api.dto.InventoryDTO;
import com.rfidentity.model.Inventory;

import java.util.List;

public interface InventoryService {
    void save(Inventory inventory);
}
