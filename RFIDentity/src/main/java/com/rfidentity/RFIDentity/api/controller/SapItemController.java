package com.rfidentity.RFIDentity.api.controller;

import com.rfidentity.RFIDentity.api.dto.ApiResponse;
import com.rfidentity.RFIDentity.api.dto.DiffSapItemDTO;
import com.rfidentity.RFIDentity.service.InventoryService;
import com.rfidentity.RFIDentity.service.SapItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api/sapItem")
public class SapItemController {

    @Autowired
    private SapItemService sapItemService;

    @PutMapping("/updateSapItem/{inventoryId}/{assetId}")
    public ResponseEntity<ApiResponse> updateSapItem(
            @PathVariable Long inventoryId,
            @PathVariable String assetId,
            @RequestBody DiffSapItemDTO diffSapItemDTO) {

        try{
            String responseMessage = sapItemService.updateSapItem(assetId, inventoryId, diffSapItemDTO);
            return new ResponseEntity<>(new ApiResponse(responseMessage), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
