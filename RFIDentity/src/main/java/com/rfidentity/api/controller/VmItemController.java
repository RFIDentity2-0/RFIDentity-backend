package com.rfidentity.api.controller;

import com.rfidentity.api.dto.VmItemDTO;
import com.rfidentity.service.VmItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/vmItem")
@RestController
public class VmItemController {

    @Autowired
    private VmItemService vmItemService;

    @GetMapping
    public List<VmItemDTO> getVmItem() {
        return vmItemService.getAllVmItem();
    }
}
