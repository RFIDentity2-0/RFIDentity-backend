package com.rfidentity.RFIDentity.api.controller;

import com.rfidentity.RFIDentity.api.dto.DashboardDTO;
import com.rfidentity.RFIDentity.api.dto.DiffDTO;
import com.rfidentity.RFIDentity.api.dto.DiffSapItemDTO;
import com.rfidentity.RFIDentity.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getDashboard")
    public ResponseEntity<Map<String, Object>> getAllCombinedItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long inventoryId) {

        Page<DashboardDTO> pageResult = inventoryService.getAllDashboardItems(page, size, inventoryId);

        Map<String, Object> response = new HashMap<>();
        response.put("content", pageResult.getContent());
        response.put("totalElements", pageResult.getTotalElements());
        response.put("totalPages", pageResult.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getDiff/{inventoryId}/{assetId}")
    public ResponseEntity<DiffDTO>getDiffItems(
            @PathVariable Long inventoryId,
            @PathVariable String assetId) {

        DiffDTO diffResults = inventoryService.getDiffItems(inventoryId, assetId);
        return new ResponseEntity<>(diffResults, HttpStatus.OK);
    }

    @PutMapping("/updateSapItem/{inventoryId}/{assetId}")
    public ResponseEntity<String> updateSapItem(
            @PathVariable String assetId,
            @PathVariable Long inventoryId,
            @RequestBody DiffSapItemDTO diffSapItemDTO) {

        String responseMessage = inventoryService.updateSapItem(assetId, inventoryId, diffSapItemDTO);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}