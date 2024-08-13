package com.rfidentity.RFIDentity.repo;

import com.rfidentity.RFIDentity.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {
}
