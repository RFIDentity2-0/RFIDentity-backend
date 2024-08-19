package com.rfidentity.RFIDentity.api.dto.mapper;

import com.rfidentity.RFIDentity.api.dto.InventoryDTO;
import com.rfidentity.RFIDentity.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    InventoryDTO toDTO(Inventory inventory);

    Inventory toEntity(InventoryDTO inventoryDTO);
}
