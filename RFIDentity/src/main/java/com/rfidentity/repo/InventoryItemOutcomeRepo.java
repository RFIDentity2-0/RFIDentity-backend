package com.rfidentity.repo;

import com.rfidentity.model.Inventory;
import com.rfidentity.model.InventoryItem;
import com.rfidentity.model.InventoryItemOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface InventoryItemOutcomeRepo extends JpaRepository<InventoryItemOutcome, Long> {
    List<InventoryItemOutcome> findAllByInventoryItemIdInventoryId(Inventory inventoryId);

}
