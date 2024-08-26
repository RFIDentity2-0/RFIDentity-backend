package com.rfidentity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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

    @Column(name = "capitalized_date")
    private LocalDate capitalizedDate;

    @Column(name = "description")
    private String description;

    @Column(name = "room")
    private String room;

    public void setRowNum(Integer rowNum) {
    }

    public void setCellValue(String cellValue) {
    }
}
