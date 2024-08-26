package com.rfidentity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Mapping for DB view
 */
@Data
@Entity
@Table(name = "current_inventory_assets")
@IdClass(CurrentInventoryAssetId.class)
public class CurrentInventoryAsset {

    @Id
    @Column(name = "inventory_id")
    private Integer inventoryId;

    @Id
    @Size(max = 64)
    @Column(name = "asset_id", length = 64)
    private String assetId;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Size(max = 255)
    @Column(name = "sap_room")
    private String sapRoom;

    @Column(name = "status", length = Integer.MAX_VALUE)
    private String status;

    @Size(max = 255)
    @Column(name = "location")
    private String location;

    @Size(max = 255)
    @Column(name = "building")
    private String building;

    @Size(max = 255)
    @Column(name = "room")
    private String room;

    @Size(max = 255)
    @Column(name = "item_status")
    private String itemStatus;

}
