package com.rfidentity.RFIDentity.service;

import com.rfidentity.RFIDentity.api.dto.DiffSapItemDTO;

import java.util.List;

public interface SapItemService {
    List<String> getUniqueRooms(Long inventoryId);
    String updateSapItem(String assetId, Long inventoryId, DiffSapItemDTO diffSapItemDTO);
}
