package com.rfidentity.repo;

import com.rfidentity.model.Inventory;
import com.rfidentity.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemRepo extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findAllByInventoryId(Inventory inventoryId);
}
