package com.rfidentity.service;

import com.rfidentity.api.dto.InventoryDTO;
import com.rfidentity.api.dto.DashboardDTO;
import com.rfidentity.api.dto.DiffDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    Page<DashboardDTO> getAllDashboardItems(int page, int size, Long inventoryId);
    DiffDTO getDiffItems(Long inventoryId, String assetId);
    List<InventoryDTO> getAllInventory();
    Map<String, Object> getUniqueRooms(Long inventoryId, Pageable pageable);
}
