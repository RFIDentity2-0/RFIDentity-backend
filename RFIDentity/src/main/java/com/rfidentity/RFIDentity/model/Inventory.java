package com.rfidentity.RFIDentity.model;

import jakarta.persistence.*;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryItem> items) {
        this.items = items;
    }
    public List<VmItem> getVmItems() {
        return vmItems;
    }

    public void setVmItems(List<VmItem> vmItems) {
        this.vmItems = vmItems;
    }

    public List<VmItem> getSapItems() {
        return sapItems;
    }

    public void setSapItems(List<VmItem> sapItems) {
        this.sapItems = sapItems;
    }
}
