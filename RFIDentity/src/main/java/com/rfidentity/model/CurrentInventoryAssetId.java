package com.rfidentity.model;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CurrentInventoryAssetId implements Serializable {

    @Column(name = "inventory_id")
    private Integer inventoryId;

    @Size(max = 64)
    @Column(name = "asset_id", length = 64)
    private String assetId;

}
