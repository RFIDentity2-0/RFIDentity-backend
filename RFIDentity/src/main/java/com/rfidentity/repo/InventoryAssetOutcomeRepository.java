package com.rfidentity.repo;

import com.rfidentity.model.InventoryAssetsOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryAssetOutcomeRepository extends JpaRepository<InventoryAssetsOutcome, Long> {

    @Query("SELECT MAX(io.inventoryId) FROM InventoryAssetsOutcome io WHERE io.assetId = :assetId")
    Optional<Long> findLatestInventoryIdForAsset(@Param("assetId") String assetId);

    Optional<InventoryAssetsOutcome> findByInventoryIdAndAssetId(Long inventoryId, String assetId);

    @Query("SELECT COUNT(io) > 0 FROM InventoryAssetsOutcome io WHERE io.inventoryId = :inventoryId AND io.assetId = :assetId")
    boolean existsByInventoryIdAndAssetId(@Param("inventoryId") Long inventoryId, @Param("assetId") String assetId);
}
