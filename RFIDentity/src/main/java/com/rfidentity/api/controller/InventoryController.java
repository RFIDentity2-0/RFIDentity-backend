package com.rfidentity.api.controller;

import com.rfidentity.api.dto.InventoryDTO;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @GetMapping
    public List<InventoryDTO> getAllInventory() {
        return null;
    }

}
