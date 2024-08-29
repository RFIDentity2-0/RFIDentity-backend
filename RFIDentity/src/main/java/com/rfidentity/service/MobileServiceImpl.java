package com.rfidentity.service;

import com.rfidentity.api.dto.MobileAssetDetailDTO;
import com.rfidentity.api.dto.MobileOutcomeUpdateDTO;
import com.rfidentity.model.InventoryAssetsOutcome;
import com.rfidentity.repo.InventoryRepository;
import com.rfidentity.repo.InventoryAssetOutcomeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MobileServiceImpl implements MobileService {

    private final InventoryAssetOutcomeRepository outcomeRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public void addAssetsOutcome(MobileOutcomeUpdateDTO mobileOutcomeUpdateDTO) {
        Long latestInventoryId = inventoryRepository.findLatestInventoryId()
                .orElseThrow(() -> new EntityNotFoundException("No inventory found"));

        LocalDate currentDate = LocalDate.now();

        for (MobileAssetDetailDTO asset : mobileOutcomeUpdateDTO.getAssets()) {

            Optional<InventoryAssetsOutcome> existingOutcome = outcomeRepository
                    .findByInventoryIdAndAssetId(latestInventoryId, asset.getAssetId());

            InventoryAssetsOutcome outcome;
            if (existingOutcome.isPresent()) {
                outcome = existingOutcome.get();
                outcome.setLocation(mobileOutcomeUpdateDTO.getLocation());
                outcome.setStatus(asset.getStatus());
                outcome.setComment(asset.getComment());
                outcome.setScannedDate(currentDate);
            } else {
                outcome = new InventoryAssetsOutcome();
                outcome.setInventoryId(latestInventoryId);
                outcome.setAssetId(asset.getAssetId());
                outcome.setLocation(mobileOutcomeUpdateDTO.getLocation());
                outcome.setStatus(asset.getStatus());
                outcome.setComment(asset.getComment());
                outcome.setScannedDate(currentDate);
            }

            outcomeRepository.save(outcome);
        }
    }
}
