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

    public ResponseEntity<SapVmAssetDTO> getAssetById(@PathVariable String assetId) {
        return ResponseEntity.ok(sapVmAssetService.getAssetById(assetId));
    }

    @Override
    public ResponseEntity<Void> updateSapItem(@PathVariable Long inventoryId, @PathVariable String assetId, @RequestBody SapItemUpdateDTO dto) {
        sapVmAssetService.updateSapItem(inventoryId, assetId, dto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> updateVmItem(@PathVariable Long inventoryId, @PathVariable String assetId, @RequestBody VmItemUpdateDTO dto) {
        sapVmAssetService.updateVmItem(inventoryId, assetId, dto);
        return ResponseEntity.noContent().build();
    }
}
