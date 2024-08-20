package com.rfidentity.RFIDentity.api.controller;

import com.rfidentity.RFIDentity.api.dto.SapItemDTO;
import com.rfidentity.RFIDentity.service.SapItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api/sapItem")
public class SapItemController {

    @Autowired
    private SapItemService sapItemService;

    @GetMapping("/getUniqueRooms/{inventoryId}")
    public List<String> getAllRooms(@PathVariable Long inventoryId){
        return sapItemService.getUniqueRooms(inventoryId);
    }
}
