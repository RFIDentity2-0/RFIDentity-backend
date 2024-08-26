package com.rfidentity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
public class LocationAssetsSummary {

    @Id
    @Column(name = "asset_id")
    private String assetId;

    @Column(name = "location")
    private String location;

    @Column(name = "asset_count")
    private Long assetCount;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;
}
