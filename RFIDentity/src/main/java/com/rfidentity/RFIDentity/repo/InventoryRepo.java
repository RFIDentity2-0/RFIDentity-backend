package com.rfidentity.RFIDentity.repo;

import com.rfidentity.RFIDentity.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface InventoryRepo extends JpaRepository<Inventory, Long> {
}
