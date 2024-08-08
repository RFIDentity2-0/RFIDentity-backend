package com.rfidentity.RFIDentity.controller;

import com.rfidentity.RFIDentity.model.InventoryItem;
import com.rfidentity.RFIDentity.repo.InventoryItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryItemController {

    @Autowired
    private InventoryItemRepo inventoryItemRepo;

    @RequestMapping("/api/inventoryItem")
    public void addInventoryItem(@RequestBody InventoryItem inventoryItem) {
        inventoryItemRepo.save(inventoryItem);
    }
}
