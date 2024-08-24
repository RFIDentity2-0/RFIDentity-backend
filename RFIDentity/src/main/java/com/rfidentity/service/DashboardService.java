package com.rfidentity.service;

import com.rfidentity.api.dto.CurrentInventoryAssetDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DashboardService {

    Page<CurrentInventoryAssetDTO> getDashboardItems(Pageable pageable);

}
