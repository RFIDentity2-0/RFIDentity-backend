package com.rfidentity.service;

import com.rfidentity.api.dto.CommentDTO;
import com.rfidentity.api.dto.InsideRoomDTO;
import com.rfidentity.api.dto.InventoryDTO;
import com.rfidentity.api.dto.DashboardDTO;
import com.rfidentity.api.dto.DiffDTO;
import com.rfidentity.api.dto.RoomItemDTO;
import com.rfidentity.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    Page<DashboardDTO> getAllDashboardItems(int page, int size, Long inventoryId);
    DiffDTO getDiffItems(Long inventoryId, String assetId);
    List<InventoryDTO> getAllInventory();

    void save(Inventory inventory);

    Page<Map<String, Object>> getUniqueRooms(Long inventoryId, int page, int size);
    Page<InsideRoomDTO> getAssetForRooms(int page, int size, Long inventoryId, String room);

    String updateCommentForAssets(Long inventoryId, String assetId, CommentDTO commentDTO);


}
