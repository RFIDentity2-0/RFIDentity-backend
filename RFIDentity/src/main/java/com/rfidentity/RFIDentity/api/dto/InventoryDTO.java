package com.rfidentity.RFIDentity.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InventoryDTO {
    private long id;
    @JsonIgnore
    private LocalDate date;
}
