package com.rfidentity.RFIDentity.service;

import com.rfidentity.RFIDentity.api.dto.InventoryDTO;
import com.rfidentity.RFIDentity.api.dto.SapItemDTO;
import com.rfidentity.RFIDentity.model.SapItem;
import com.rfidentity.RFIDentity.repo.SapItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SapItemServiceImpl implements SapItemService {

    @Autowired
    private SapItemRepo sapItemRepo;

    @Override
    public List<SapItemDTO> getAllSapItem() {
        return StreamSupport.stream(sapItemRepo.findAll().spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SapItemDTO convertToDTO(SapItem sapItem) {
        SapItemDTO dto = new SapItemDTO();
        dto.setId(sapItem.getId());
        dto.setAssetNo(sapItem.getAssetNo());
        dto.setSubNo(sapItem.getSubNo());
        dto.setCapitalizedDate(sapItem.getCapitalizedDate());
        dto.setDescription(sapItem.getDescription());
        dto.setRoom(sapItem.getRoom());
        dto.setAssetId(sapItem.getAssetId());

        dto.setInventoryId(sapItem.getInventoryId().getId());

        return dto;
    }
}
