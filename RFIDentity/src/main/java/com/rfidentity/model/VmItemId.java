package com.rfidentity.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VmItemId implements Serializable {

    private Long inventoryId;
    private String assetId;
}
