package com.rfidentity.service;

import com.rfidentity.api.dto.SapItemUpdateDTO;
import com.rfidentity.api.dto.SapVmAssetDTO;
import com.rfidentity.api.dto.VmItemUpdateDTO;

public interface SapVmAssetService {
    SapVmAssetDTO getAssetById(String assetId);
    void updateSapItem(String assetId, SapItemUpdateDTO dto);
    void updateVmItem(String assetId, VmItemUpdateDTO dto);
}
