package com.rfidentity.service;

import com.rfidentity.model.Inventory;
import com.rfidentity.model.SapItem;
import com.rfidentity.model.VmItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
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
    private final SapItemService sapItemService;
    private final SAPFileProcessor sapFileProcessor;
    private final VmItemService vmItemService;
    private final VMFileProcessor vmFileProcessor;

    @Override
    public void process(Path file,Path file2) {
        log.info(String.format("Init processing file %s", file.getFileName()));
        Inventory inventory = new Inventory();
        inventory.setDate(LocalDate.now());
        inventoryService.save(inventory);
        try {
            System.out.println("0");
            Map<Integer, List<String>> data = sapFileProcessor.readExcel(new File("src/main/resources/SAPVM/SAP_20240414.xlsx"));
            System.out.println("1");
            Map<Integer, List<String>> data2 = vmFileProcessor.readExcel(new File("src/main/resources/SAPVM/VM_20240414.xlsx"));
            System.out.println("2");


            data.forEach((rowNum, rowData) -> {
                SapItem sapItem = new SapItem();
                sapItem.setInventoryId(inventory);
                sapItem.setAssetNo(Long.parseLong(rowData.get(0)));
                sapItem.setSubNo(Long.parseLong(rowData.get(1)));
                sapItem.setDescription(rowData.get(3));
                sapItem.setRoom(rowData.get(4));
                sapItem.setAssetId(rowData.get(5));
                sapItemService.save(sapItem);
            });

            data2.forEach((rowNum, rowData) -> {
                VmItem vmItem = new VmItem();
                vmItem.setInventoryId(inventory);
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
            log.error("Error processing file", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void process(Path file) {

    }
}