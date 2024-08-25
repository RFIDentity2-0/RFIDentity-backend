package com.rfidentity.repo;

import com.rfidentity.model.SapItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SapItemRepository extends JpaRepository<SapItem, String> {
    Optional<SapItem> findByInventoryIdAndAssetId(Long inventoryId, String assetId);
}
