package com.rfidentity.service;

import com.rfidentity.model.Inventory;
import com.rfidentity.model.SapItem;
import com.rfidentity.model.VmItem;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExcelFileProcessor implements FileProcessor {

    private final InventoryService inventoryService;
    private final SAPFileProcessor sapFileProcessor;
    private final VmItemService vmItemService;
    private final SapItemService sapItemService;
    private final VMFileProcessor vmFileProcessor;
    private long inventory_id;

    @Override
    @Transactional
    public void process(Path sapFilePath, Path vmFilePath) {

        log.info(String.format("Init processing sapFilePath %s", sapFilePath.getFileName()));
        Inventory inventory = new Inventory();
        inventory.setDate(LocalDate.now().atStartOfDay());
        inventoryService.save(inventory);

        inventory_id = inventory.getId();
        try {
            Map<Integer, List<String>> data = sapFileProcessor.readExcel(sapFilePath.toFile());
            Map<Integer, List<String>> data2 = vmFileProcessor.readExcel(vmFilePath.toFile());

            data.forEach((rowNum, rowData) -> {
                SapItem sapItem = new SapItem();
                sapItem.setInventoryId(inventory_id);
                sapItem.setDescription(rowData.get(3));
                sapItem.setRoom(rowData.get(4));
                sapItem.setAssetId(rowData.get(5));
                sapItemService.save(sapItem);
            });

            data2.forEach((rowNum, rowData) -> {
                VmItem vmItem = new VmItem();
                vmItem.setInventoryId(inventory_id);
                vmItem.setSystemName(rowData.get(1));
                vmItem.setDnsName(rowData.get(2));
                vmItem.setType(rowData.get(3));
                vmItem.setManufacturer(rowData.get(4));
                vmItem.setHardwareType(rowData.get(5));
                vmItem.setSerialNo(rowData.get(6));
                vmItem.setAssetId(rowData.get(0));


                vmItemService.save(vmItem);
            });
        } catch (IOException e) {
            log.error("Error processing sapFilePath", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void process(Path file) {
        log.info(String.format(
                "Init processing file %s", file.getFileName()));
    }

}
