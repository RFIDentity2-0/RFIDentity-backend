package com.rfidentity.RFIDentity.controller;

import com.rfidentity.RFIDentity.model.InventoryItemOutcome;
import com.rfidentity.RFIDentity.repo.InventoryItemOutcomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryItemOutcomeController {

    @Autowired
    private InventoryItemOutcomeRepo inventoryItemOutcomeRepo;

    @RequestMapping("/api/inventoryItemOutcome")
    public void addInventoryItemOutcome(@RequestBody InventoryItemOutcome inventoryItemOutcome) {
        inventoryItemOutcomeRepo.save(inventoryItemOutcome);
    }
}
