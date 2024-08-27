package com.rfidentity.repo;

import com.rfidentity.model.CurrentLocationsWithAssetsNumber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrentLocationWithAssetsNumberRepository
        extends JpaRepository<CurrentLocationsWithAssetsNumber, String> {

    Page<CurrentLocationsWithAssetsNumber> findByLocationLike(String locationName, Pageable pageable);

    default Page<CurrentLocationsWithAssetsNumber> findByLocationLikeName(String locationName, Pageable pageable) {
        return findByLocationLike("%" + locationName + "%", pageable);
    }
}
