package com.rfidentity.service;

import com.rfidentity.api.dto.InventoryDTO;
import com.rfidentity.model.Inventory;
import com.rfidentity.repo.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;
    @Autowired
    private SapItemService sapItemService;
    @Autowired
    private VmItemService vmItemService;
    @Autowired
    private InventoryItemService inventoryItemService;

    @Override
    public List<InventoryDTO> getAllInventory() {
        return inventoryRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addInventory(Inventory inventory) {
        inventoryRepo.save(inventory);
    }

    private InventoryDTO convertToDTO(Inventory inventory) {
        InventoryDTO dto = new InventoryDTO();
        dto.setId(inventory.getId());
        dto.setDate(inventory.getDate());

        return dto;
    }
}
