package com.rfidentity.RFIDentity.repo;

import com.rfidentity.RFIDentity.model.Inventory;
import com.rfidentity.RFIDentity.model.SapItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SapItemRepo extends JpaRepository<SapItem, Long> {
    List<SapItem> findByInventoryId(Inventory inventory, Pageable pageable);
    SapItem findByInventoryIdAndAssetId(Inventory inventory, String assetId);
    Optional<SapItem> findByAssetIdAndInventoryId(String assetId, Inventory inventoryId);
    List<SapItem> findAllByInventoryId(Inventory inventory);

}
