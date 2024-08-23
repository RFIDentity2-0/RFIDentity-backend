package com.rfidentity.repo;

import com.rfidentity.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {
    @Query("SELECT i FROM Inventory i ORDER BY i.id DESC")
    List<Inventory> findFirstByOrderByIdDesc();
}
