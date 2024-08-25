package com.rfidentity.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vm_item")
@IdClass(VmItemId.class)
public class VmItem {
    @Id
    @Column(name = "inventory_id")
    private Long inventoryId;

    @Id
    @Column(name = "asset_id", length = 64)
    private String assetId;

    @Column(name = "system_name")
    private String systemName;

    @Column(name = "dns_name")
    private String dnsName;

    @Column(name = "type")
    private String type;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "hardware_type")
    private String hardwareType;

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "status")
    private String status;

    @Column(name = "department")
    private String department;

    @Column(name = "location")
    private String location;

    @Column(name = "building")
    private String building;

    @Column(name = "room")
    private String room;

}
