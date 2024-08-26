package com.rfidentity.service;

import com.rfidentity.api.dto.SapItemDTO;
import com.rfidentity.model.SapItem;
import com.rfidentity.repo.SapItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SapItemServiceImpl implements SapItemService {

    @Autowired
    private SapItemRepository sapItemRepo;

    @Override
    public void save(SapItem sapItem) {
        sapItemRepo.save(sapItem);
    }
}
