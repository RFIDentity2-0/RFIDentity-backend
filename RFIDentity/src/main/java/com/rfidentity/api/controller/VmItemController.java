package com.rfidentity.api.controller;

import com.rfidentity.service.VmItemService;
import com.rfidentity.api.dto.ApiResponse;
import com.rfidentity.api.dto.DiffVmItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/vmItem")
@RestController
public class VmItemController {

    @Autowired
    private VmItemService vmItemService;

    @PutMapping("/updateVmItem/{inventoryId}/{assetId}")
    public ResponseEntity<ApiResponse> updateVmItem(
            @PathVariable Long inventoryId,
            @PathVariable String assetId,
            @RequestBody DiffVmItemDTO diffVmItemDTO) {

        try{
            String responseMessage = vmItemService.updateVmItem(assetId, inventoryId, diffVmItemDTO);
            return new ResponseEntity<>(new ApiResponse(responseMessage), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
