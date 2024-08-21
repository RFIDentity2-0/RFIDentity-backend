package com.rfidentity.service;

import com.rfidentity.api.dto.SapItemDTO;
import com.rfidentity.model.SapItem;

import java.util.List;

public interface SapItemService {
    List<SapItemDTO> getAllSapItem();
    void deleteAll();
    void save(SapItem sapItem);

    boolean isDatabaseEmpty();
}
