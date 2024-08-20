package com.rfidentity.RFIDentity.api.dto.mapper;

import com.rfidentity.RFIDentity.api.dto.DiffSapItemDTO;
import com.rfidentity.RFIDentity.api.dto.DiffVmItemDTO;
import com.rfidentity.RFIDentity.api.dto.InventoryDTO;
import com.rfidentity.RFIDentity.model.Inventory;
import com.rfidentity.RFIDentity.model.SapItem;
import com.rfidentity.RFIDentity.model.VmItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    InventoryDTO toDTO(Inventory inventory);
    Inventory toEntity(InventoryDTO inventoryDTO);

    @Mapping(target = "id", ignore = true)
    void updateDiffSapItemFromDto(DiffSapItemDTO dto, @MappingTarget SapItem entity);

    @Mapping(target = "id", ignore = true)
    void updateDiffVmItemFromDto(DiffVmItemDTO dto, @MappingTarget VmItem entity);

}
