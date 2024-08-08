package com.rfidentity.RFIDentity.repo;

import com.rfidentity.RFIDentity.model.InventoryItemOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemOutcomeRepo extends JpaRepository<InventoryItemOutcome, Long> {
}
