package com.rfidentity.repo;

import com.rfidentity.model.LocationAssetsSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationAssetsSummaryRepository extends JpaRepository<LocationAssetsSummary, String> {

    Page<LocationAssetsSummary> findByLocationLike(String locationName, Pageable pageable);

    default Page<LocationAssetsSummary> findByLocationLikeName(String locationName, Pageable pageable) {
        return findByLocationLike("%" + locationName + "%", pageable);
    }
}
