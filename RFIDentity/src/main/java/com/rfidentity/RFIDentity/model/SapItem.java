package com.rfidentity.RFIDentity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "sapItem")
public class SapItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventoryId;

    @Column(name = "asset_no")
    private long assetNo;

    @Column(name = "sub_no")
    private long subNo;

    @Column(name = "capitalized_date")
    private LocalDate capitalizedDate;

    @Column(name = "description")
    private String description;

    @Column(name = "room")
    private String room;

    @Column(name = "asset_id")
    private String assetId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Inventory getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Inventory inventoryId) {
        this.inventoryId = inventoryId;
    }

    public long getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(long assetNo) {
        this.assetNo = assetNo;
    }

    public long getSubNo() {
        return subNo;
    }

    public void setSubNo(long subNo) {
        this.subNo = subNo;
    }

    public LocalDate getCapitalizedDate() {
        return capitalizedDate;
    }

    public void setCapitalizedDate(LocalDate capitalizedDate) {
        this.capitalizedDate = capitalizedDate;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
