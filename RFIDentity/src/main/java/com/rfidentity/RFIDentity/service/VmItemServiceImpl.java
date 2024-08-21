package com.rfidentity.RFIDentity.service;

import com.rfidentity.RFIDentity.api.dto.DiffVmItemDTO;
import com.rfidentity.RFIDentity.api.dto.mapper.InventoryMapper;
import com.rfidentity.RFIDentity.model.Inventory;
import com.rfidentity.RFIDentity.repo.InventoryRepo;
import com.rfidentity.RFIDentity.model.VmItem;
import com.rfidentity.RFIDentity.repo.VmItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VmItemServiceImpl implements VmItemService {

    @Autowired
    private VmItemRepo vmItemRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    private final InventoryMapper inventoryMapper = InventoryMapper.INSTANCE;

    public String updateVmItem(String assetId, Long inventoryId, DiffVmItemDTO diffVmItemDTO) {
        Inventory inventory = inventoryRepo.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        VmItem vmItem = vmItemRepo.findByAssetIdAndInventoryId(assetId, inventory)
                .orElseThrow(() -> new RuntimeException("VmItem not found"));

        inventoryMapper.updateDiffVmItemFromDto(diffVmItemDTO, vmItem);

        vmItemRepo.save(vmItem);

        return "VmItem updated successfully";
    }
}
