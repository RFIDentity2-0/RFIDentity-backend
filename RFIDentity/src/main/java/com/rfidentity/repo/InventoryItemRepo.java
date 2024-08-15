package com.rfidentity.repo;

import com.rfidentity.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRepo extends JpaRepository<InventoryItem, Long> {
}
