package com.rfidentity.service;

import com.rfidentity.api.dto.DiffSapItemDTO;
import com.rfidentity.model.SapItem;

public interface SapItemService {
    void save(SapItem sapItem);
    String updateSapItem(String assetId, Long inventoryId, DiffSapItemDTO diffSapItemDTO);

}