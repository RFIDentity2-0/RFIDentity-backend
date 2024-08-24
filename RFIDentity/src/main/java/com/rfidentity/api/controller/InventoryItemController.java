package com.rfidentity.api.controller;

import com.rfidentity.api.dto.InventoryItemDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/inventoryItem")
public class InventoryItemController {

    @GetMapping
    public List<InventoryItemDTO> getAllInventoryItem() {
        return null;
    }

}
