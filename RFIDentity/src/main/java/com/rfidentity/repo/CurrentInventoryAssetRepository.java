package com.rfidentity.repo;

import com.rfidentity.model.CurrentInventoryAsset;
import com.rfidentity.model.CurrentInventoryAssetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrentInventoryAssetRepository
        extends JpaRepository<CurrentInventoryAsset, CurrentInventoryAssetId>,
        JpaSpecificationExecutor<CurrentInventoryAsset> {

}
