package com.rfidentity.RFIDentity.service;

import com.rfidentity.RFIDentity.api.dto.DashboardDTO;
import com.rfidentity.RFIDentity.api.dto.DiffDTO;
import com.rfidentity.RFIDentity.api.dto.InventoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {
    Page<DashboardDTO> getAllDashboardItems(int page, int size, Long inventoryId);
    DiffDTO getDiffItems(Long inventoryId, String assetId);
    List<InventoryDTO> getAllInventory();
}
