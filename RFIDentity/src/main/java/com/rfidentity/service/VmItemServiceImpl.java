package com.rfidentity.service;

import com.rfidentity.api.dto.VmItemDTO;
import com.rfidentity.model.VmItem;
import com.rfidentity.repo.VmItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VmItemServiceImpl implements VmItemService {

    @Autowired
    private VmItemRepository vmItemRepo;

    @Override
    public void save(VmItem vmItem) {
        vmItemRepo.save(vmItem);
    }
}
