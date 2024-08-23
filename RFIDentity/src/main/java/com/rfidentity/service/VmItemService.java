package com.rfidentity.service;

import com.rfidentity.api.dto.VmItemDTO;
import com.rfidentity.model.VmItem;

import java.util.List;

public interface VmItemService {
    List<VmItemDTO> getAllVmItem();

    void save(VmItem vmItem);
}
