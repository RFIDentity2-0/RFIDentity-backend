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
@Table(name = "vmItem")
public class VmItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = true)
    private Inventory inventoryId;

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

    @Column(name = "date_of_installation")
    private LocalDate dateOfInstallation;

    @Column(name = "status")
    private String status;

    @Column(name = "department")
    private String department;

    @Column(name = "person_id")
    private String personId;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "location")
    private String location;

    @Column(name = "building")
    private String building;

    @Column(name = "room")
    private String room;

    @Column(name = "asset_id")
    private String assetId;

}
