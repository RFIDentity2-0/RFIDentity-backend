package com.rfidentity.service;

import com.rfidentity.api.dto.VmItemUpdateDTO;

public interface MobileService {
    void updateItemStatus(String assetId, VmItemUpdateDTO dto);
}
