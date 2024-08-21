package com.rfidentity.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

}
