package com.rfidentity.repo;

import com.rfidentity.model.CurrentInventoryAsset;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class CurrentInventoryAssetSpecification {

    public static Specification<CurrentInventoryAsset> assetSpecification(
            String assetId,
            String description
    ) {

        return (root, query, criteriaBuilder) -> {
            Predicate idPredicate =
                    criteriaBuilder.like(
                            root.get("assetId"),
                            !StringUtils.isEmpty(assetId) ? likePattern(assetId) : null
                    );
            Predicate descriptionPredicate =
                    criteriaBuilder.like(
                            root.get("description"),
                            !StringUtils.isEmpty(description) ? likePattern(description) : null
                    );

            List<Predicate> includeInSearch = new ArrayList<>(8);

            if (!StringUtils.isEmpty(assetId)) {
                includeInSearch.add(idPredicate);
            }
            if (!StringUtils.isEmpty(description)) {
                includeInSearch.add(descriptionPredicate);
            }
            Predicate[] array = includeInSearch.toArray(new Predicate[0]);
            return criteriaBuilder.and(includeInSearch.toArray(array));
        };
    }

    private static String likePattern(String value) {
        return "%" + value + "%";
    }
}
