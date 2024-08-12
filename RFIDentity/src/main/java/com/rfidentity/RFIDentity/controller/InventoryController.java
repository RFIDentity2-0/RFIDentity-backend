package com.rfidentity.RFIDentity.controller;

import com.rfidentity.RFIDentity.model.Inventory;
import com.rfidentity.RFIDentity.repo.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepo inventoryRepo;


    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryRepo.findAll();
    }

    @GetMapping("/{id}")
    public Inventory getInventory(@PathVariable long id) {
        return inventoryRepo.findById(id).get();
    }

    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryRepo.save(inventory);
    }

    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable long id, @RequestBody Inventory inventory) {
        Inventory existingInventory = inventoryRepo.findById(id).get();
        existingInventory.setDate(inventory.getDate());
        return inventoryRepo.save(existingInventory);
    }

    @DeleteMapping("/{id}")
    public String deleteInventory(@PathVariable long id) {
        try{
            inventoryRepo.deleteById(id);
            return "Inventory deleted successfully";
        }catch (Exception e) {
            return "User not found";
        }
    }
}
