package com.rfidentity.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrentInventoryAssetDTO {

    private Integer inventoryId;

    private String assetId;

    private String description;

    private String sapRoom;

    private String status;

    private String location;

    private String building;

    private String room;

    private String itemStatus;

}
