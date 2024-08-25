package com.rfidentity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sap_vm_asset_view")
public class SapVmAssetView {

    @Id
    @Column(name = "sap_asset_id")
    private String sapAssetId;

    @Column(name = "description")
    private String description;

    @Column(name = "sap_room")
    private String sapRoom;

    @Column(name = "vm_asset_id")
    private String vmAssetId;

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
}
