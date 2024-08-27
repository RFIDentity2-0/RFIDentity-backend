package com.rfidentity.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class MobileOutcomeUpdateDTO {
    private String location;
    private List<MobileAssetDetailDTO> assets;
}
