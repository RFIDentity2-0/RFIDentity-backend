package com.rfidentity.RFIDentity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "inventoryItemOutcome")
public class InventoryItemOutcome {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_item_id", nullable = false)
    private InventoryItem inventoryItemId;

    @Column(name = "location")
    private String location;

    @Column(name = "comment")
    private String comment;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "asset_id")
    private String assetId;

    @Column(name = "scanned_date")
    private LocalDate scannedDate;

}
