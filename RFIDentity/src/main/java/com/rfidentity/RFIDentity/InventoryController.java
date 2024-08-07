package com.rfidentity.RFIDentity;

import com.rfidentity.RFIDentity.model.Inventory;
import com.rfidentity.RFIDentity.repo.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @Autowired
    InventoryRepo inventoryRepo;

    @PostMapping("/addInventory")
    public void addInventory(@RequestBody Inventory inventory) {
        inventoryRepo.save(inventory);
    }
}
