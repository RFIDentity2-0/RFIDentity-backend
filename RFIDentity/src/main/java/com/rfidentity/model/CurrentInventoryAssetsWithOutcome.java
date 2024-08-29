package com.rfidentity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "current_inventory_assets_with_outcome")
@Data
public class CurrentInventoryAssetsWithOutcome {

    @Id
    @Column(name = "asset_id")
    private String assetId;

    @Column(name = "description")
    private String description;

    @Column(name = "inventory_status")
    private String inventoryStatus;

    @Column(name = "comment")
    private String comment;

    @Column(name = "location")
    private String location;

}