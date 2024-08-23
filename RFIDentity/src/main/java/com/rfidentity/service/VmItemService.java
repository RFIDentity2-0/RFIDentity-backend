package com.rfidentity.service;

import com.rfidentity.api.dto.DiffVmItemDTO;

public interface VmItemService {
    String updateVmItem(String assetId, Long inventoryId, DiffVmItemDTO diffVmItemDTO);
}
