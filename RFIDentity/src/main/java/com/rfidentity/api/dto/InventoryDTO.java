package com.rfidentity.api.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class InventoryDTO {
    private long id;
    private LocalDate date;
}
