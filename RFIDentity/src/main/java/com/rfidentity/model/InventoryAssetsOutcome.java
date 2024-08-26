package com.rfidentity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "inventory_assets_outcome")
@Data
public class InventoryAssetsOutcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inventory_id")
    private Long inventoryId;

    @Column(name = "asset_id")
    private String assetId;

    @Column(name = "location")
    private String location;

    @Column(name = "status")
    private String status;

    @Column(name = "comment")
    private String comment;

    @Column(name = "scanned_date")
    private LocalDate scannedDate;
}
