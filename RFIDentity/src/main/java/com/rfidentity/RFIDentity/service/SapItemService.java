package com.rfidentity.RFIDentity.service;

import com.rfidentity.RFIDentity.api.dto.DiffSapItemDTO;

public interface SapItemService {
    String updateSapItem(String assetId, Long inventoryId, DiffSapItemDTO diffSapItemDTO);
}
