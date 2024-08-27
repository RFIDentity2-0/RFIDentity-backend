package com.rfidentity.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SapItemUpdateDTO {
    private String description;
    private String sapRoom;
}
