package com.rfidentity.repo;

import com.rfidentity.model.VmItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VmItemRepository extends JpaRepository<VmItem, String> {
    Optional<VmItem> findByInventoryIdAndAssetId(Long inventoryId, String assetId);

    @Query("SELECT MAX(inventoryId) FROM VmItem")
    Optional<Long> findLatestInventoryId();

}
