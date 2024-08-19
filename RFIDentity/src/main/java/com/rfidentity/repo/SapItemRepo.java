package com.rfidentity.repo;

import com.rfidentity.model.SapItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SapItemRepo extends JpaRepository<SapItem, Long> {
}
