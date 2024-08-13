package com.rfidentity.RFIDentity.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date")
    private LocalDate date;

    @OneToMany(mappedBy = "inventoryId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryItem> items;

    @OneToMany(mappedBy = "inventoryId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VmItem> vmItems;

    @OneToMany(mappedBy = "inventoryId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VmItem> sapItems;

}
