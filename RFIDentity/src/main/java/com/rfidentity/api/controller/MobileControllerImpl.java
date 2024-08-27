package com.rfidentity.api.controller;

import com.rfidentity.api.dto.MobileOutcomeUpdateDTO;
import com.rfidentity.api.dto.VmItemUpdateDTO;
import com.rfidentity.service.MobileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MobileControllerImpl implements MobileController {

    private final MobileService mobileService;

    @Override
    public ResponseEntity<Void> addAssetsOutcome(
            MobileOutcomeUpdateDTO mobileOutcomeUpdateDTO
    ) {
        mobileService.addAssetsOutcome(mobileOutcomeUpdateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
