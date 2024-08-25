package com.rfidentity.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sap_item")
@IdClass(SapItemId.class)
public class SapItem {
    @Id
    @Column(name = "inventory_id")
    private Long inventoryId;

    @Id
    @Column(name = "asset_id", length = 64)
    private String assetId;

    @Column(name = "description")
    private String description;

    @Column(name = "room")
    private String room;

}
