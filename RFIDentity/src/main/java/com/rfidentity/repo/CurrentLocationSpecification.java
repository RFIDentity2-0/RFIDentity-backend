package com.rfidentity.repo;

import com.rfidentity.model.CurrentInventoryAsset;
import com.rfidentity.model.CurrentLocationsWithAssetsNumber;
import com.rfidentity.model.LocationAssetsSummary;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CurrentLocationSpecification {

    public static Specification<LocationAssetsSummary> locationSpecification(
            String location
    ) {

        return (root, query, criteriaBuilder) -> {
            Predicate locationPredicate =
                    criteriaBuilder.like(
                            root.get("location"),
                            !StringUtils.isEmpty(location) ? likePattern(location) : null
                    );

            List<Predicate> includeInSearch = new ArrayList<>(8);

            if (!StringUtils.isEmpty(location)) {
                includeInSearch.add(locationPredicate);
            }

            Predicate[] array = includeInSearch.toArray(new Predicate[0]);
            return criteriaBuilder.and(includeInSearch.toArray(array));
        };
    }

    private static String likePattern(String value) {
        return "%" + value + "%";
    }

}
