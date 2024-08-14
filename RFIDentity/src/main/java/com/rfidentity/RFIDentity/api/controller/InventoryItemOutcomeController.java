package com.rfidentity.RFIDentity.api.controller;

import com.rfidentity.RFIDentity.api.dto.InventoryItemOutcomeDTO;
import com.rfidentity.RFIDentity.service.InventoryItemOutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventoryItemOutcome")
public class InventoryItemOutcomeController {

    @Autowired
    private InventoryItemOutcomeService inventoryItemOutcomeService;

    @GetMapping
    public List<InventoryItemOutcomeDTO> getAllInventoryItemOutcome() {
        return inventoryItemOutcomeService.getAllInventoryItemOutcome();
    }

}
