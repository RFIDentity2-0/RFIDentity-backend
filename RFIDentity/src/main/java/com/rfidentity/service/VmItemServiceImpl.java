package com.rfidentity.service;

import com.rfidentity.api.dto.VmItemDTO;
import com.rfidentity.model.SapItem;
import com.rfidentity.model.VmItem;
import com.rfidentity.repo.VmItemRepo;
import com.rfidentity.api.dto.DiffVmItemDTO;
import com.rfidentity.api.dto.mapper.InventoryMapper;
import com.rfidentity.model.Inventory;
import com.rfidentity.repo.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VmItemServiceImpl implements VmItemService {
    @Override
    public void save(VmItem vmItem) {
        vmItemRepo.save(vmItem);
    }
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
