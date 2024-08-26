package com.rfidentity.repo;

import com.rfidentity.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Inventory findTopByOrderByIdDesc();
}
