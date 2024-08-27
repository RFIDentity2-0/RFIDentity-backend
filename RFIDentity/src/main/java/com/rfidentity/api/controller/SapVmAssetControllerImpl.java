package com.rfidentity.api.controller;
import com.rfidentity.api.dto.SapItemUpdateDTO;
import com.rfidentity.api.dto.SapVmAssetDTO;
import com.rfidentity.api.dto.VmItemUpdateDTO;
import com.rfidentity.service.SapVmAssetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SapVmAssetControllerImpl implements SapVmAssetController {

    private final SapVmAssetService sapVmAssetService;

    @Override
    public ResponseEntity<SapVmAssetDTO> getAssetById(
            final String assetId
    ) {
        return ResponseEntity.ok(sapVmAssetService.getAssetById(assetId));
    }

    @Override
    public ResponseEntity<String> updateSapItem(
            String assetId,
            SapItemUpdateDTO dto
    ) {
        sapVmAssetService.updateSapItem(assetId, dto);
        return ResponseEntity.ok("SapItem updated successfully");
    }

    @Override
    public ResponseEntity<String> updateVmItem(
            String assetId,
            VmItemUpdateDTO dto
    ) {
        sapVmAssetService.updateVmItem(assetId, dto);
        return ResponseEntity.ok("VmItem updated successfully");

    }
}
