package com.rfidentity.service;

import com.rfidentity.api.dto.SapItemUpdateDTO;
import com.rfidentity.api.dto.SapVmAssetDTO;
import com.rfidentity.api.dto.VmItemUpdateDTO;

public interface SapVmAssetService {
    SapVmAssetDTO getAssetById(String assetId);
    void updateSapItem(Long inventoryId, String assetId, SapItemUpdateDTO dto);
    void updateVmItem(Long inventoryId, String assetId, VmItemUpdateDTO dto);
}
