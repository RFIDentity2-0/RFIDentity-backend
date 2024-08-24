package com.rfidentity.api.controller;

import com.rfidentity.api.dto.CurrentInventoryAssetDTO;
import com.rfidentity.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class DashboardControllerImpl implements DashboardController {

    private final DashboardService dashboardService;

    @Override
    public ResponseEntity<Page<CurrentInventoryAssetDTO>> getDashboardItems(
            @SortDefault(sort = "status,desc") @PageableDefault(size = 20) final Pageable pageable
    ) {
        return  ResponseEntity.ok(dashboardService.getDashboardItems(pageable));
    }
}
