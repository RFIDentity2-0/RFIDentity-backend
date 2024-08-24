package com.rfidentity.api.controller;

import com.rfidentity.api.dto.SapItemDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api/sapItem")
public class SapItemController {

    @GetMapping
    public List<SapItemDTO> getAllSapItems() {
        return null;
    }
}
