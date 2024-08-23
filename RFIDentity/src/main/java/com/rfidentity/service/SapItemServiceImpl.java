package com.rfidentity.service;

import com.rfidentity.model.SapItem;
import com.rfidentity.repo.SapItemRepo;
import com.rfidentity.api.dto.DiffSapItemDTO;
import com.rfidentity.model.Inventory;
import com.rfidentity.repo.InventoryRepo;
import com.rfidentity.api.dto.mapper.InventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SapItemServiceImpl implements SapItemService {

    @Autowired
    private SapItemRepo sapItemRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    private final InventoryMapper inventoryMapper = InventoryMapper.INSTANCE;

    public String updateSapItem(String assetId, Long inventoryId, DiffSapItemDTO diffSapItemDTO) {
        Inventory inventory = inventoryRepo.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        SapItem sapItem = sapItemRepo.findByAssetIdAndInventoryId(assetId, inventory)
                .orElseThrow(() -> new RuntimeException("SapItem not found"));

        inventoryMapper.updateDiffSapItemFromDto(diffSapItemDTO, sapItem);

        sapItemRepo.save(sapItem);

        return "SapItem updated successfully";
    }

    @Override
    public void save(SapItem sapItem) {
        sapItemRepo.save(sapItem);
    }

}
