package com.rfidentity.repo;

import com.rfidentity.model.SapVmAssetView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SapVmAssetViewRepository extends JpaRepository<SapVmAssetView, String> {
}
