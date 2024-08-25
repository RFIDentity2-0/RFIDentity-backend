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
            String description,
            Integer inventoryId
    ) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(assetId)) {
                predicates.add(criteriaBuilder.like(root.get("assetId"), likePattern(assetId)));
            }
            if (!StringUtils.isEmpty(description)) {
                predicates.add(criteriaBuilder.like(root.get("description"), likePattern(description)));
            }

            predicates.add(criteriaBuilder.equal(root.get("inventoryId"), inventoryId));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static String likePattern(String value) {
        return "%" + value + "%";
    }
}
