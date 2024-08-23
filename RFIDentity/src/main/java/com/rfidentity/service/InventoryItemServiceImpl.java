package com.rfidentity.service;

import com.rfidentity.api.dto.InventoryItemDTO;
import com.rfidentity.model.InventoryItem;
import com.rfidentity.repo.InventoryItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {

    @Autowired
    private InventoryItemRepo inventoryItemRepo;



}
