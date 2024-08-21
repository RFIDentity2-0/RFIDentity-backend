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
    public Page<Map<String, List<Map<String, String>>>> getUniqueRooms(Long inventoryId, int page, int size) {
        Inventory inventory = inventoryRepo.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        // Pobierz dane z obu repozytoriów
        List<SapItem> sapItems = sapItemRepo.findAllByInventoryId(inventory);
        List<VmItem> vmItems = vmItemRepo.findAllByInventoryId(inventory);

        // Utwórz mapę do przechowywania połączonych danych według pokoju
        Map<String, List<Map<String, String>>> roomMap = new HashMap<>();

        // Utwórz mapę do szybkiego dostępu do VmItem na podstawie assetId
        Map<String, VmItem> vmItemMap = vmItems.stream()
                .collect(Collectors.toMap(VmItem::getAssetId, Function.identity()));

        for (SapItem sapItem : sapItems) {
            String assetId = sapItem.getAssetId();
            VmItem vmItem = vmItemMap.get(assetId);

            // Określ wartość room na podstawie dostępnych danych
            String room = (sapItem.getRoom() != null && !sapItem.getRoom().isEmpty())
                    ? sapItem.getRoom()
                    : (vmItem != null && vmItem.getRoom() != null && !vmItem.getRoom().isEmpty())
                    ? vmItem.getRoom()
                    : "Unknown Room";

            Map<String, String> itemDetails = new HashMap<>();
            itemDetails.put("assetId", assetId);
            itemDetails.put("description", sapItem.getDescription());
            itemDetails.put("status", vmItem != null ? vmItem.getStatus() : null);

            // Dodaj do odpowiedniej listy pokoju
            roomMap.computeIfAbsent(room, k -> new ArrayList<>()).add(itemDetails);
        }

        // Logika paginacji
        List<Map<String, List<Map<String, String>>>> paginatedRooms = roomMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sortuj pokoje alfabetycznie
                .skip(page * size)
                .limit(size)
                .map(entry -> {
                    Map<String, List<Map<String, String>>> singleRoom = new HashMap<>();
                    singleRoom.put(entry.getKey(), entry.getValue().stream()
                            .sorted(Comparator.comparing(item -> item.get("assetId"))) // Sortuj elementy według assetId
                            .limit(10) // Ogranicz do pierwszych 10 elementów na pokój
                            .collect(Collectors.toList()));
                    return singleRoom;
                })
                .collect(Collectors.toList());

        // Utwórz odpowiedź w formacie Page
        int totalRooms = roomMap.size();
        Page<Map<String, List<Map<String, String>>>> pagedResult = new PageImpl<>(
                paginatedRooms, PageRequest.of(page, size), totalRooms);

        return pagedResult;
    }
}