package com.rfidentity.RFIDentity.service;

import com.rfidentity.RFIDentity.api.dto.InventoryDTO;
import com.rfidentity.RFIDentity.api.dto.InventoryItemDTO;
import com.rfidentity.RFIDentity.api.dto.InventoryItemOutcomeDTO;
import com.rfidentity.RFIDentity.model.InventoryItem;
import com.rfidentity.RFIDentity.model.InventoryItemOutcome;
import com.rfidentity.RFIDentity.repo.InventoryItemOutcomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class InventoryItemOutcomeServiceImpl implements InventoryItemOutcomeService {

    @Autowired
    private InventoryItemOutcomeRepo inventoryItemOutcomeRepo;

    @Override
    public List<InventoryItemOutcomeDTO> getAllInventoryItemOutcome() {
        return StreamSupport.stream(inventoryItemOutcomeRepo.findAll().spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private InventoryItemOutcomeDTO convertToDTO(InventoryItemOutcome inventoryItemOutcome) {
        InventoryItemOutcomeDTO dto = new InventoryItemOutcomeDTO();
        dto.setId(inventoryItemOutcome.getId());
        dto.setLocation(inventoryItemOutcome.getLocation());
        dto.setComment(inventoryItemOutcome.getComment());
        dto.setStatus(inventoryItemOutcome.getStatus());
        dto.setDescription(inventoryItemOutcome.getDescription());
        dto.setAssetId(inventoryItemOutcome.getAssetId());
        dto.setStatus(inventoryItemOutcome.getStatus());

        if (inventoryItemOutcome.getInventoryItemId() != null) {
            InventoryItemDTO inventoryItemDTO = new InventoryItemDTO();
            inventoryItemDTO.setId(inventoryItemOutcome.getInventoryItemId().getId());
            inventoryItemDTO.setSapItemId(inventoryItemOutcome.getInventoryItemId().getSapItemId());
            inventoryItemDTO.setVmItemId(inventoryItemOutcome.getInventoryItemId().getVmItemId());

            if (inventoryItemOutcome.getInventoryItemId().getInventoryId() != null) {
                InventoryDTO inventoryDTO = new InventoryDTO();
                inventoryDTO.setId(inventoryItemOutcome.getInventoryItemId().getInventoryId().getId());
                inventoryDTO.setDate(inventoryItemOutcome.getInventoryItemId().getInventoryId().getDate());
                inventoryItemDTO.setInventoryId(inventoryDTO);
            }

            dto.setInventoryItemId(inventoryItemDTO);
        }

        return dto;
    }

}
