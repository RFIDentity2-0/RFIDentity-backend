package com.rfidentity.service;

import com.rfidentity.api.dto.InventoryDTO;
import com.rfidentity.model.Inventory;
import com.rfidentity.repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepo;


    public void save(Inventory inventory) {
        inventoryRepo.save(inventory);
    }
}
