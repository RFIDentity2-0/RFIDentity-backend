package com.rfidentity.service;

import com.rfidentity.model.SapItem;
import com.rfidentity.repo.SapItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SapItemServiceImpl implements SapItemService {

    @Autowired
    private SapItemRepository sapItemRepository;

    @Override
    public void save(SapItem sapItem) {sapItemRepository.save(sapItem);}

}
