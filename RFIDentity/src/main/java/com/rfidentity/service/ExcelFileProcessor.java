package com.rfidentity.service;

import com.rfidentity.model.SapItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExcelFileProcessor implements FileProcessor {
    private InventoryService inventoryService;
    SAPFileProcessor instance = new SAPFileProcessor();
    private final SapItemService sapItemService;
    private final SAPFileProcessor sapFileProcessor;
    public void process(Path file) {
        log.info(String.format(
                "Init processing file %s", file.getFileName()));

        try {
            Map<Integer, List<String>> data = instance.readExcel(new File("src/main/resources/SAP_20240414.xlsx"));
            data.forEach((rowNum, rowData) -> {
                SapItem sapItem = new SapItem();

                sapItem.setAssetNo(Long.parseLong(rowData.get(0)));
                sapItem.setSubNo(Long.parseLong(rowData.get(1)));

                sapItem.setDescription(rowData.get(3));
                sapItem.setRoom(rowData.get(4));
                sapItem.setAssetId(rowData.get(5));
                sapItemService.save(sapItem);
            });



        }catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
