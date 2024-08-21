package com.rfidentity.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomItemDTO {
    private String assetId;
    private String description;
    private String status;
    private String room;
}
