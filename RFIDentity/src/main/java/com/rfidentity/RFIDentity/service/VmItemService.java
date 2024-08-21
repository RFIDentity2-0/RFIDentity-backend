package com.rfidentity.RFIDentity.service;

import com.rfidentity.RFIDentity.api.dto.DiffVmItemDTO;

public interface VmItemService {
    String updateVmItem(String assetId, Long inventoryId, DiffVmItemDTO diffVmItemDTO);
}
