package com.rfidentity.RFIDentity.repo;

import com.rfidentity.RFIDentity.model.VmItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VmItemRepo extends JpaRepository<VmItem, Long> {
}
