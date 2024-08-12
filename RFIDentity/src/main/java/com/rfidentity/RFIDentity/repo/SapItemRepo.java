package com.rfidentity.RFIDentity.repo;

import com.rfidentity.RFIDentity.model.SapItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SapItemRepo extends CrudRepository<SapItem, Long> {
}
