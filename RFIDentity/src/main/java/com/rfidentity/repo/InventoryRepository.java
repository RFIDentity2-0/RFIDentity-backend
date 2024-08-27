package com.rfidentity.repo;

import com.rfidentity.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    @Query("SELECT MAX(i.id) FROM Inventory i")
    Optional<Long> findLatestInventoryId();
}
