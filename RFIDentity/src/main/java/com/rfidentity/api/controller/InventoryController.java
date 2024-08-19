package com.rfidentity.api.controller;

import com.rfidentity.api.dto.InventoryDTO;
import com.rfidentity.model.Inventory;
import com.rfidentity.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<InventoryDTO> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @PostMapping
    public String addInventory(@RequestBody Inventory inventory) {
        inventoryService.addInventory(inventory);
        return "Inventory added successfully";
    }

}
