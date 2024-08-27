package com.rfidentity.service;

import com.rfidentity.model.Inventory;
import com.rfidentity.repo.InventoryRepository;

import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;

    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }
}
