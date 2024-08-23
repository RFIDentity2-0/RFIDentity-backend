package com.rfidentity.service;

import com.rfidentity.api.dto.VmItemDTO;
import com.rfidentity.model.VmItem;
import com.rfidentity.repo.VmItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VmItemServiceImpl implements VmItemService {

    @Autowired
    private VmItemRepo vmItemRepo;

    @Override
    public List<VmItemDTO> getAllVmItem() {

        return StreamSupport.stream(vmItemRepo.findAll().spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private VmItemDTO convertToDTO(VmItem vmItem) {
        VmItemDTO dto = new VmItemDTO();
        dto.setId(vmItem.getId());
        dto.setSystemName(vmItem.getSystemName());
        dto.setDnsName(vmItem.getDnsName());
        dto.setType(vmItem.getType());
        dto.setManufacturer(vmItem.getManufacturer());
        dto.setHardwareType(vmItem.getHardwareType());
        dto.setSerialNo(vmItem.getSerialNo());
        dto.setDateOfInstallation(vmItem.getDateOfInstallation());
        dto.setStatus(vmItem.getStatus());
        dto.setDepartment(vmItem.getDepartment());
        dto.setPersonId(vmItem.getPersonId());
        dto.setLastName(vmItem.getLastName());
        dto.setFirstName(vmItem.getFirstName());
        dto.setLocation(vmItem.getLocation());
        dto.setBuilding(vmItem.getBuilding());
        dto.setRoom(vmItem.getRoom());
        dto.setAssetId(vmItem.getAssetId());

        dto.setInventoryId(vmItem.getInventoryId().getId());

        return dto;
    }
}
