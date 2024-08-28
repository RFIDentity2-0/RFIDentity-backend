package com.rfidentity.repo;

import com.rfidentity.model.CurrentInventoryAsset;
import com.rfidentity.model.LocationAssetsSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationAssetsSummaryRepository extends JpaRepository<LocationAssetsSummary, String>, JpaSpecificationExecutor<CurrentInventoryAsset> {

    List<LocationAssetsSummary> findByLocationIn(List<String> locations);

}
