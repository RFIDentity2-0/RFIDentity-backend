package com.rfidentity.service;

import com.rfidentity.api.dto.InsideRoomDTO;
import com.rfidentity.api.dto.InventoryDTO;
import com.rfidentity.model.Inventory;
import com.rfidentity.model.InventoryItem;
import com.rfidentity.model.InventoryItemOutcome;
import com.rfidentity.repo.InventoryItemOutcomeRepo;
import com.rfidentity.repo.InventoryItemRepo;
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

    @Autowired
    private InventoryItemRepo inventoryItemRepo;

    @Autowired
    private InventoryItemOutcomeRepo inventoryItemOutcomeRepo;

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
    public Page<Map<String, Object>> getUniqueRooms(Long inventoryId, int page, int size) {
        Inventory inventory = inventoryRepo.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        List<SapItem> sapItems = sapItemRepo.findAllByInventoryId(inventory);
        List<InventoryItem> inventoryItems = inventoryItemRepo.findAllByInventoryId(inventory);
        List<InventoryItemOutcome> inventoryItemOutcomes = inventoryItemOutcomeRepo.findAllByInventoryItemIdInventoryId(inventory);
        List<VmItem> vmItems = vmItemRepo.findAllByInventoryId(inventory);

        Map<String, InventoryItem> inventoryItemMap = inventoryItems.stream()
                .collect(Collectors.toMap(InventoryItem::getSapItemId, Function.identity()));

        Map<String, String> assetIdToStatusMap = inventoryItemOutcomes.stream()
                .collect(Collectors.toMap(InventoryItemOutcome::getAssetId, InventoryItemOutcome::getStatus));

        Map<String, VmItem> vmItemMap = vmItems.stream()
                .collect(Collectors.toMap(VmItem::getAssetId, Function.identity()));


        Map<String, List<Map<String, String>>> roomMap = new HashMap<>();

        for (SapItem sapItem : sapItems) {
            String assetId = sapItem.getAssetId();
            InventoryItem inventoryItem = inventoryItemMap.get(sapItem.getAssetId());
            String status = assetIdToStatusMap.getOrDefault(assetId, "Unknown Status");
            VmItem vmItem = vmItemMap.get(assetId);

            String room = (sapItem.getRoom() != null && !sapItem.getRoom().isEmpty())
                    ? sapItem.getRoom()
                    : (vmItem != null && vmItem.getRoom() != null && !vmItem.getRoom().isEmpty())
                    ? vmItem.getRoom()
                    : "Unknown Room";

            Map<String, String> itemDetails = new HashMap<>();
            itemDetails.put("assetId", assetId);
            itemDetails.put("description", sapItem.getDescription());
            itemDetails.put("status", status);

            roomMap.computeIfAbsent(room, k -> new ArrayList<>()).add(itemDetails);
        }

        List<Map<String, Object>> paginatedRooms = roomMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .skip(page * size)
                .limit(size)
                .map(entry -> {
                    Map<String, Object> singleRoom = new HashMap<>();
                    singleRoom.put("Room", entry.getKey());
                    singleRoom.put("Items", entry.getValue().stream()
                            .sorted(Comparator.comparing(item -> item.get("assetId")))
                            .limit(10)
                            .collect(Collectors.toList()));
                    return singleRoom;
                })
                .collect(Collectors.toList());

        int totalRooms = roomMap.size();
        Page<Map<String, Object>> pagedResult = new PageImpl<>(
                paginatedRooms, PageRequest.of(page, size), totalRooms);

        return pagedResult;
    }


    @Override
    public Page<InsideRoomDTO> getAssetForRooms(int page, int size, Long inventoryId, String room){
        Pageable pageable = PageRequest.of(page, size);

        Inventory inventory = inventoryRepo.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        List<SapItem> sapRooms = sapItemRepo.findAllByInventoryId(inventory);
        List<InventoryItem> inventoryItems = inventoryItemRepo.findAllByInventoryId(inventory);
        List<InventoryItemOutcome> inventoryItemOutcomes = inventoryItemOutcomeRepo.findAllByInventoryItemIdInventoryId(inventory);
        List<VmItem> vmRooms = vmItemRepo.findAllByInventoryId(inventory);

        boolean isRoomInSap = sapRooms.stream()
                .anyMatch(sapRoom -> sapRoom.getRoom() != null && sapRoom.getRoom().equalsIgnoreCase(room));

        boolean isRoomInVm = vmRooms.stream()
                .anyMatch(vmRoom -> vmRoom.getRoom() != null && vmRoom.getRoom().equalsIgnoreCase(room));



        Map<String, SapItem> sapItemMap = sapRooms.stream()
                .collect(Collectors.toMap(SapItem::getAssetId, Function.identity()));

        Map<String, VmItem> vmItemMap = vmRooms.stream()
                .collect(Collectors.toMap(VmItem::getAssetId, Function.identity()));

        Map<String, InventoryItemOutcome> inventoryItemOutcomesMap = inventoryItemOutcomes.stream()
                .collect(Collectors.toMap(InventoryItemOutcome::getAssetId, Function.identity()));

        Set<String> allAssetIds = new HashSet<>();
        allAssetIds.addAll(sapItemMap.keySet());
        allAssetIds.addAll(vmItemMap.keySet());
        allAssetIds.addAll(inventoryItemOutcomesMap.keySet());

        List<InsideRoomDTO> roomDTO = new ArrayList<>();


        for (String assetId : allAssetIds) {

            if(isRoomInSap || isRoomInVm){
                SapItem sapItem = sapItemMap.get(assetId);
                VmItem vmItem = vmItemMap.get(assetId);
                InventoryItemOutcome inventoryItemOutcome = inventoryItemOutcomesMap.get(assetId);

                InsideRoomDTO dto = new InsideRoomDTO();
                dto.setAssetId(assetId);
                dto.setDescription(sapItem != null ? sapItem.getDescription() : null);
                dto.setHardwareType(vmItem != null ? vmItem.getHardwareType() : null);
                dto.setType(vmItem != null ? vmItem.getType() : null);
                dto.setStatus(inventoryItemOutcome != null ? inventoryItemOutcome.getStatus() : null);

                roomDTO.add(dto);
            }

        }

        return new PageImpl<>(roomDTO, pageable, roomDTO.size());
    }
}