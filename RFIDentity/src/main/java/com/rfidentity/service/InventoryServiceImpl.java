package com.rfidentity.service;

import com.rfidentity.api.dto.InventoryDTO;
import com.rfidentity.model.Inventory;
import com.rfidentity.repo.InventoryRepo;
import com.rfidentity.api.dto.DashboardDTO;
import com.rfidentity.api.dto.DiffDTO;
import com.rfidentity.api.dto.RoomItemDTO;
import com.rfidentity.api.dto.mapper.InventoryMapper;
import com.rfidentity.model.SapItem;
import com.rfidentity.model.VmItem;
import com.rfidentity.repo.SapItemRepo;
import com.rfidentity.repo.VmItemRepo;
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

    private final InventoryMapper inventoryMapper = InventoryMapper.INSTANCE;

    @Override
    public Page<DashboardDTO> getAllDashboardItems(int page, int size, Long inventoryId) {
        Pageable pageable = PageRequest.of(page, size);

        List<Inventory> inventories = inventoryRepo.findFirstByOrderByIdDesc();

        Inventory selectedInventory;
        if (inventoryId != null) {
            selectedInventory = inventoryRepo.findById(inventoryId)
                    .orElseThrow(() -> new RuntimeException("Inventory not found"));
        } else {
            if (inventories.isEmpty()) {
                throw new RuntimeException("No inventories found");
            } else {
                selectedInventory = inventories.get(0);
            }
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
    public List<InventoryDTO> getAllInventory() {
        return inventoryRepo.findFirstByOrderByIdDesc().stream()
                .map(inventoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getUniqueRooms(Long inventoryId, Pageable pageable) {

        Inventory inventory = inventoryRepo.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        List<SapItem> sapItems = sapItemRepo.findAllByInventoryId(inventory);
        List<VmItem> vmItems = vmItemRepo.findAllByInventoryId(inventory);

        Map<String, List<RoomItemDTO>> roomItemsMap = new HashMap<>();

        for (SapItem sapItem : sapItems) {
            String room = sapItem.getRoom() != null ? sapItem.getRoom() : "Unknown Room";
            String assetId = sapItem.getAssetId();
            String description = sapItem.getDescription();

            RoomItemDTO roomItemDTO = new RoomItemDTO(assetId, description, null);

            roomItemsMap.computeIfAbsent(room, k -> new ArrayList<>()).add(roomItemDTO);
        }

        for (VmItem vmItem : vmItems) {
            String room = vmItem.getLocation() != null ? vmItem.getLocation() : "Unknown Room";
            String assetId = vmItem.getAssetId();
            String status = vmItem.getStatus();

            RoomItemDTO roomItemDTO = new RoomItemDTO(assetId, null, status);

            roomItemsMap.computeIfAbsent(room, k -> new ArrayList<>()).add(roomItemDTO);
        }

        roomItemsMap.forEach((room, items) -> {
            List<RoomItemDTO> sortedItems = items.stream()
                    .sorted(Comparator.comparing(RoomItemDTO::getAssetId))
                    .limit(10)
                    .collect(Collectors.toList());
            roomItemsMap.put(room, sortedItems);
        });

        List<Map.Entry<String, List<RoomItemDTO>>> roomItemsList = new ArrayList<>(roomItemsMap.entrySet());


        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), roomItemsList.size());

        List<Map.Entry<String, List<RoomItemDTO>>> paginatedRooms = roomItemsList.subList(start, end);

        Map<String, Object> response = new HashMap<>();
        response.put("totalElements", roomItemsList.size());
        response.put("totalPages", (int) Math.ceil((double) roomItemsList.size() / pageable.getPageSize()));
        response.put("content", paginatedRooms);

        return response;
    }
}