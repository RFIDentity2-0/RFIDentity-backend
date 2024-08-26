package com.rfidentity.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class CurrentLocationWithAssetsNumberDTO {

    private String location;
    private LocalDate scannedDate;
    private Long count;

}
