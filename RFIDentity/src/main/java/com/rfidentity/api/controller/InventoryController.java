package com.rfidentity.api.controller;

import com.rfidentity.api.dto.ApiResponse;
import com.rfidentity.api.dto.CommentDTO;
import com.rfidentity.api.dto.DiffSapItemDTO;
import com.rfidentity.api.dto.InsideRoomDTO;
import com.rfidentity.api.dto.InventoryDTO;
import com.rfidentity.api.dto.RoomItemDTO;
import com.rfidentity.model.Inventory;
import com.rfidentity.model.SapItem;
import com.rfidentity.service.InventoryService;
import com.rfidentity.api.dto.DashboardDTO;
import com.rfidentity.api.dto.DiffDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getUniqueRooms")
    public ResponseEntity<Map<String, Object>> getUniqueRooms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam Long inventoryId) {

        Page<Map<String, Object>> resultPage = inventoryService.getUniqueRooms(inventoryId, page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("content", resultPage.getContent());
        response.put("totalElements", resultPage.getTotalElements());
        response.put("totalPages", resultPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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

    @GetMapping("/getAllInventories")
    public ResponseEntity<List<InventoryDTO>> getAllInventory(){
        List<InventoryDTO> responseInventory = inventoryService.getAllInventory();
        return new ResponseEntity<>(responseInventory, HttpStatus.OK);
    }

    @GetMapping("/getAssetsForRoom/{inventoryId}/{room}")
    public ResponseEntity<Map<String, Object>> getAssetsForRoom(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable(required = false) Long inventoryId,
            @PathVariable String room){

        Page<InsideRoomDTO> pageResult = inventoryService.getAssetForRooms(page, size, inventoryId, room);

        Map<String, Object> response = new HashMap<>();
        response.put("content", pageResult.getContent());
        response.put("totalElements", pageResult.getTotalElements());
        response.put("totalPages", pageResult.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateCommentForAssets/{inventoryId}/{assetId}")
    public ResponseEntity<ApiResponse> updateCommentForAssets(
            @PathVariable Long inventoryId,
            @PathVariable String assetId,
            @RequestBody CommentDTO commentDTO){
        try{
            String responseMessage = inventoryService.updateCommentForAssets(inventoryId, assetId, commentDTO);
            return new ResponseEntity<>(new ApiResponse(responseMessage),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
}