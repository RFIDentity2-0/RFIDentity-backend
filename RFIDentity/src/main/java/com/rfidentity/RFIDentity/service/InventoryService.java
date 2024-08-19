package com.rfidentity.RFIDentity.service;

import com.rfidentity.RFIDentity.api.dto.DashboardDTO;
import com.rfidentity.RFIDentity.api.dto.DiffDTO;
import com.rfidentity.RFIDentity.api.dto.DiffSapItemDTO;
import com.rfidentity.RFIDentity.api.dto.DiffVmItemDTO;
import com.rfidentity.RFIDentity.api.dto.InventoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {
    Page<DashboardDTO> getAllDashboardItems(int page, int size, Long inventoryId);
    DiffDTO getDiffItems(Long inventoryId, String assetId);
    String updateSapItem(String assetId, Long inventoryId, DiffSapItemDTO diffSapItemDTO);
    String updateVmItem(String assetId, Long inventoryId, DiffVmItemDTO diffVmItemDTO);
    List<InventoryDTO> getAllInventory();
}
