package com.rfidentity.RFIDentity.service;


import com.rfidentity.RFIDentity.api.dto.InventoryDTO;
import com.rfidentity.RFIDentity.api.dto.InventoryItemDTO;
import com.rfidentity.RFIDentity.model.InventoryItem;
import com.rfidentity.RFIDentity.repo.InventoryItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {

    @Autowired
    private InventoryItemRepo inventoryItemRepo;

    @Override
    public List<InventoryItemDTO> getAllInventoryItem() {
        return StreamSupport.stream(inventoryItemRepo.findAll().spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private InventoryItemDTO convertToDTO(InventoryItem inventoryItem) {
        InventoryItemDTO dto = new InventoryItemDTO();
        dto.setId(inventoryItem.getId());
        dto.setSapItemId(inventoryItem.getSapItemId());
        dto.setVmItemId(inventoryItem.getVmItemId());


        return dto;
    }
}
