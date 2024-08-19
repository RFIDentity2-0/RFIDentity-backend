package com.rfidentity.api.controller;

import com.rfidentity.api.dto.InventoryItemDTO;
import com.rfidentity.service.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/inventoryItem")
public class InventoryItemController {

    @Autowired
    private InventoryItemService inventoryItemService;

    @GetMapping
    public List<InventoryItemDTO> getAllInventoryItem() {
        return inventoryItemService.getAllInventoryItem();
    }

}
