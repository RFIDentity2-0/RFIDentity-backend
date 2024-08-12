package com.rfidentity.RFIDentity.controller;

import com.rfidentity.RFIDentity.model.SapItem;
import com.rfidentity.RFIDentity.repo.SapItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SapItemController {

    @Autowired
    private SapItemRepo sapItemRepo;

    @RequestMapping("/api/sapItem")
    public void addSapItem(@RequestBody SapItem sapItem) {
        sapItemRepo.save(sapItem);
    }
}
