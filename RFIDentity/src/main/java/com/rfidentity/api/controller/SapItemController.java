package com.rfidentity.api.controller;

import com.rfidentity.api.dto.SapItemDTO;
import com.rfidentity.service.SapItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api/sapItem")
public class SapItemController {

    @Autowired
    private SapItemService sapItemService;

    @GetMapping
    public List<SapItemDTO> getAllSapItems() {
        return sapItemService.getAllSapItem();
    }
}
