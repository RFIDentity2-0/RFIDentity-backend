package com.rfidentity.RFIDentity.repo;

import com.rfidentity.RFIDentity.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRepo extends JpaRepository<InventoryItem, Long> {
}
