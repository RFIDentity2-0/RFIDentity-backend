package com.rfidentity.service;

import com.rfidentity.api.dto.SapItemUpdateDTO;
import com.rfidentity.api.dto.SapVmAssetDTO;
import com.rfidentity.api.dto.VmItemUpdateDTO;
import com.rfidentity.model.SapItem;
import com.rfidentity.model.SapVmAssetView;
import com.rfidentity.model.VmItem;
import com.rfidentity.repo.SapItemRepository;
import com.rfidentity.repo.SapVmAssetViewRepository;
import com.rfidentity.repo.VmItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SapVmAssetServiceImpl implements SapVmAssetService {

    private final SapVmAssetViewRepository repository;
    private final SapItemRepository sapItemRepository;
    private final VmItemRepository vmItemRepository;

    @Override
    public SapVmAssetDTO getAssetById(String assetId) {
        SapVmAssetView sapVmAsset = repository.findById(assetId).orElse(null);

        if (sapVmAsset == null) {
            throw new EntityNotFoundException("Asset not found with id: " + assetId);
        }

        return SapVmAssetDTO.builder()
                .withSapAssetId(sapVmAsset.getSapAssetId())
                .withDescription(sapVmAsset.getDescription())
                .withSapRoom(sapVmAsset.getSapRoom())
                .withVmAssetId(sapVmAsset.getVmAssetId())
                .withSystemName(sapVmAsset.getSystemName())
                .withDnsName(sapVmAsset.getDnsName())
                .withType(sapVmAsset.getType())
                .withManufacturer(sapVmAsset.getManufacturer())
                .withHardwareType(sapVmAsset.getHardwareType())
                .withSerialNo(sapVmAsset.getSerialNo())
                .withStatus(sapVmAsset.getStatus())
                .withDepartment(sapVmAsset.getDepartment())
                .build();
    }


    public void updateSapItem(Long inventoryId, String assetId, SapItemUpdateDTO dto) {
        SapItem sapItem = sapItemRepository.findByInventoryIdAndAssetId(inventoryId, assetId)
                .orElseThrow(() -> new EntityNotFoundException("SAP item not found with inventoryId: " + inventoryId + " and assetId: " + assetId));

        sapItem.setDescription(dto.getDescription());
        sapItem.setRoom(dto.getSapRoom());

        sapItemRepository.save(sapItem);
    }


    public void updateVmItem(Long inventoryId, String assetId, VmItemUpdateDTO dto) {
        VmItem vmItem = vmItemRepository.findByInventoryIdAndAssetId(inventoryId, assetId)
                .orElseThrow(() -> new EntityNotFoundException("VM item not found with inventoryId: " + inventoryId + " and assetId: " + assetId));

        vmItem.setSystemName(dto.getSystemName());
        vmItem.setDnsName(dto.getDnsName());
        vmItem.setType(dto.getType());
        vmItem.setManufacturer(dto.getManufacturer());
        vmItem.setHardwareType(dto.getHardwareType());
        vmItem.setSerialNo(dto.getSerialNo());
        vmItem.setStatus(dto.getStatus());
        vmItem.setDepartment(dto.getDepartment());
        vmItem.setLocation(dto.getLocation());
        vmItem.setBuilding(dto.getBuilding());
        vmItem.setRoom(dto.getRoom());

        vmItemRepository.save(vmItem);
    }
}
