package com.rfidentity.RFIDentity.controller;

import com.rfidentity.RFIDentity.model.VmItem;
import com.rfidentity.RFIDentity.repo.VmItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VmItemController {

    @Autowired
    private VmItemRepo vmItemRepo;

    @RequestMapping("/api/vmItem")
    public void addVmItem(@RequestBody VmItem vmItem) {
        vmItemRepo.save(vmItem);
    }
}
