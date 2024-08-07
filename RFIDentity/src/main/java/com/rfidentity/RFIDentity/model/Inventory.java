package com.rfidentity.RFIDentity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    private long id;
    private LocalDate date;
}
