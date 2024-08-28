package com.rfidentity.service;

import com.rfidentity.model.Inventory;
import com.rfidentity.repo.InventoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }
}
