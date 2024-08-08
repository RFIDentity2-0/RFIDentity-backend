package com.rfidentity.RFIDentity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "inventoryItem")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventoryId;

    @OneToMany(mappedBy = "inventoryItemId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryItemOutcome> inventoryItemsOutcome;

    @Column(name = "sap_item_id")
    private String sapItemId;

    @Column(name = "vm_item_id")
    private String vmItemId;

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

    public List<InventoryItemOutcome> getInventoryItemsOutcome() {
        return inventoryItemsOutcome;
    }

    public void setInventoryItemsOutcome(List<InventoryItemOutcome> inventoryItemsOutcome) {
        this.inventoryItemsOutcome = inventoryItemsOutcome;
    }

    public String getSapItemId() {
        return sapItemId;
    }

    public void setSapItemId(String sapItemId) {
        this.sapItemId = sapItemId;
    }

    public String getVmItemId() {
        return vmItemId;
    }

    public void setVmItemId(String vmItemId) {
        this.vmItemId = vmItemId;
    }
}
