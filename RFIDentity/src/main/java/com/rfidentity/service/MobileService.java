package com.rfidentity.service;

import com.rfidentity.api.dto.MobileOutcomeUpdateDTO;
import com.rfidentity.api.dto.VmItemUpdateDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MobileService {

    void addAssetsOutcome(MobileOutcomeUpdateDTO mobileOutcomeUpdateDTO);
}
