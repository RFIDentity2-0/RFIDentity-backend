package com.rfidentity.model;

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
@Table(name = "sapItem")
public class SapItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = true)
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

    public void setRowNum(Integer rowNum) {
    }

    public void setCellValue(String cellValue) {
    }
}
