package com.rfidentity.RFIDentity.service;

import com.rfidentity.RFIDentity.api.dto.DashboardDTO;
import com.rfidentity.RFIDentity.api.dto.DiffDTO;
import com.rfidentity.RFIDentity.api.dto.DiffSapItemDTO;
import com.rfidentity.RFIDentity.model.Inventory;
import com.rfidentity.RFIDentity.model.SapItem;
import com.rfidentity.RFIDentity.model.VmItem;
import com.rfidentity.RFIDentity.repo.InventoryRepo;
import com.rfidentity.RFIDentity.repo.SapItemRepo;
import com.rfidentity.RFIDentity.repo.VmItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private SapItemRepo sapItemRepo;

    @Autowired
    private VmItemRepo vmItemRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    @Override
    public Page<DashboardDTO> getAllDashboardItems(int page, int size, Long inventoryId) {
        Pageable pageable = PageRequest.of(page, size);

        Inventory selectedInventory;
        if (inventoryId != null) {
            selectedInventory = inventoryRepo.findById(inventoryId)
                    .orElseThrow(() -> new RuntimeException("Inventory not found"));
        } else {
            selectedInventory = inventoryRepo.findFirstByOrderByIdDesc()
                    .orElseThrow(() -> new RuntimeException("No inventories found"));
        }

        List<SapItem> sapItems = sapItemRepo.findByInventoryId(selectedInventory, pageable);
        List<VmItem> vmItems = vmItemRepo.findByInventoryId(selectedInventory, pageable);

        Map<String, SapItem> sapItemMap = sapItems.stream()
                .collect(Collectors.toMap(SapItem::getAssetId, Function.identity()));

        Map<String, VmItem> vmItemMap = vmItems.stream()
                .collect(Collectors.toMap(VmItem::getAssetId, Function.identity()));

        Set<String> allAssetIds = new HashSet<>();
        allAssetIds.addAll(sapItemMap.keySet());
        allAssetIds.addAll(vmItemMap.keySet());

        List<DashboardDTO> dashboardItems = new ArrayList<>();

        for (String assetId : allAssetIds) {
            SapItem sapItem = sapItemMap.get(assetId);
            VmItem vmItem = vmItemMap.get(assetId);

            DashboardDTO dto = new DashboardDTO();
            dto.setAssetId(assetId);
            dto.setDescription(sapItem != null ? sapItem.getDescription() : null);
            dto.setVmLocation(vmItem != null ? vmItem.getLocation() : null);
            dto.setSapRoom(sapItem != null ? sapItem.getRoom() : null);
            dto.setStatus(vmItem != null ? vmItem.getStatus() : null);

            dashboardItems.add(dto);
        }

        return new PageImpl<>(dashboardItems, pageable, dashboardItems.size());
    }

    @Override
    public DiffDTO getDiffItems(Long inventoryId, String assetId){

        Inventory inventory = inventoryRepo.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        SapItem sapItem = sapItemRepo.findByInventoryIdAndAssetId(inventory, assetId);
        VmItem vmItem = vmItemRepo.findByInventoryIdAndAssetId(inventory, assetId);

        DiffDTO diffDTOItems = new DiffDTO();

        if(sapItem != null){
            diffDTOItems.setAssetId(sapItem.getAssetId());
            diffDTOItems.setDescription(sapItem.getDescription());
            diffDTOItems.setSapRoom(sapItem.getRoom());
        }
        if(vmItem != null){
            diffDTOItems.setAssetId(vmItem.getAssetId());
            diffDTOItems.setSystemName(vmItem.getSystemName());
            diffDTOItems.setDnsName(vmItem.getDnsName());
            diffDTOItems.setType(vmItem.getType());
            diffDTOItems.setManufacturer(vmItem.getManufacturer());
            diffDTOItems.setHardwareType(vmItem.getHardwareType());
            diffDTOItems.setSerialNo(vmItem.getSerialNo());
            diffDTOItems.setStatus(vmItem.getStatus());
            diffDTOItems.setDepartment(vmItem.getDepartment());
        }

        if (sapItem == null && vmItem == null) {
            throw new ResourceNotFoundException("No item found with assetId: " + assetId);
        }

        return diffDTOItems;
    }

    @Override
    public String updateSapItem(String assetId, Long inventoryId, DiffSapItemDTO diffSapItemDTO) {
        Inventory inventory = inventoryRepo.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        SapItem sapItem = sapItemRepo.findByAssetIdAndInventoryId(assetId, inventory)
                .orElseThrow(() -> new RuntimeException("SapItem not found"));

        sapItem.setDescription(diffSapItemDTO.getDescription());
        sapItem.setRoom(diffSapItemDTO.getRoom());

        sapItemRepo.save(sapItem);

        return "SapItem updated successfully";
    }

}