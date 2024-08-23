package com.rfidentity.repo;

import com.rfidentity.model.SapItem;
import com.rfidentity.model.VmItem;
import com.rfidentity.model.Inventory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VmItemRepo extends JpaRepository<VmItem, Long> {
    List<VmItem> findByInventoryId(Inventory inventory, Pageable pageable);
    VmItem findByInventoryIdAndAssetId(Inventory inventory, String assetId);
    Optional<VmItem> findByAssetIdAndInventoryId(String assetId, Inventory inventoryId);
    List<VmItem> findAllByInventoryId(Inventory inventory);
    List<VmItem> findAllByInventoryIdAndRoomIgnoreCase(Inventory inventory, String room);
}
