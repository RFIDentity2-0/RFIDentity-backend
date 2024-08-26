package com.rfidentity.repo;

import com.rfidentity.model.CurrentInventoryAssetsWithOutcome;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrentInventoryAssetsWithOutcomeRepository extends JpaRepository<CurrentInventoryAssetsWithOutcome, String> {

    Page<CurrentInventoryAssetsWithOutcome> findByLocation(String location, Pageable pageable);

    @Modifying
    @Query("UPDATE InventoryAssetsOutcome io SET io.comment = :comment WHERE io.inventoryId = :inventoryId AND io.assetId = :assetId")
    int updateCommentByInventoryIdAndAssetId(@Param("comment") String comment, @Param("inventoryId") Long inventoryId, @Param("assetId") String assetId);

    @Query("SELECT MAX(io.inventoryId) FROM InventoryAssetsOutcome io WHERE io.assetId = :assetId")
    Optional<Long> findLatestInventoryIdForAsset(@Param("assetId") String assetId);
}
