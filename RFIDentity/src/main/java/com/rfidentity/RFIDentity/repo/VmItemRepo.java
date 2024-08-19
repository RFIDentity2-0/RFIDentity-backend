package com.rfidentity.RFIDentity.repo;

import com.rfidentity.RFIDentity.model.Inventory;
import com.rfidentity.RFIDentity.model.SapItem;
import com.rfidentity.RFIDentity.model.VmItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VmItemRepo extends JpaRepository<VmItem, Long> {
    List<VmItem> findByInventoryId(Inventory inventory, Pageable pageable);
    VmItem findByInventoryIdAndAssetId(Inventory inventory, String assetId);
    Optional<VmItem> findByAssetIdAndInventoryId(String assetId, Inventory inventoryId);
}
