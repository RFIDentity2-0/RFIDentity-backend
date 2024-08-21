package com.rfidentity.api.dto.mapper;

import com.rfidentity.api.dto.DiffSapItemDTO;
import com.rfidentity.api.dto.DiffVmItemDTO;
import com.rfidentity.api.dto.InventoryDTO;
import com.rfidentity.model.Inventory;
import com.rfidentity.model.SapItem;
import com.rfidentity.model.VmItem;
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
