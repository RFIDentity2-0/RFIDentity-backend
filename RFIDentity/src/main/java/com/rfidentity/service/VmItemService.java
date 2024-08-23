package com.rfidentity.service;

import com.rfidentity.api.dto.DiffVmItemDTO;
import com.rfidentity.api.dto.VmItemDTO;
import com.rfidentity.model.VmItem;

import java.util.List;

public interface VmItemService {

    void save(VmItem vmItem);
    String updateVmItem(String assetId, Long inventoryId, DiffVmItemDTO diffVmItemDTO);

}
