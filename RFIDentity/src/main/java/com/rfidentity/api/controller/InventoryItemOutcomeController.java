package com.rfidentity.api.controller;

import com.rfidentity.api.dto.InventoryItemOutcomeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventoryItemOutcome")
public class InventoryItemOutcomeController {


    @GetMapping
    public List<InventoryItemOutcomeDTO> getAllInventoryItemOutcome() {
        return null;
    }

}
