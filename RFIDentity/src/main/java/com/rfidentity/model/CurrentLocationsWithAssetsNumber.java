package com.rfidentity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;


@Getter
@Setter
@Entity
@Immutable
@Table(name = "current_locations_with_assets_number")
public class CurrentLocationsWithAssetsNumber {

    @Id
    @Size(max = 255)
    @Column(name = "location")
    private String location;

    @Column(name = "scanned_date")
    private LocalDate scannedDate;

    @Column(name = "count")
    private Long count;

}
