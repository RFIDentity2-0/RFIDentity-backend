package com.rfidentity.RFIDentity.service;

import com.rfidentity.RFIDentity.model.Inventory;
import com.rfidentity.RFIDentity.model.SapItem;
import com.rfidentity.RFIDentity.repo.InventoryRepo;
import com.rfidentity.RFIDentity.repo.SapItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SapItemServiceImpl implements SapItemService {

    @Autowired
    private SapItemRepo sapItemRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    public List<String> getUniqueRooms(Long inventoryId) {

        Inventory inventory = inventoryRepo.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        List<SapItem> allItems = sapItemRepo.findByInventoryId(inventory);

        Set<String> uniqueRooms = allItems.stream()
                .map(SapItem::getRoom)
                .filter(room -> room != null && !room.isEmpty())
                .collect(Collectors.toSet());

        return uniqueRooms.stream().collect(Collectors.toList());
    }

}
