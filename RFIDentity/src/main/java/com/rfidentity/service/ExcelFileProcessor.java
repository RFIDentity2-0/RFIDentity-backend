package com.rfidentity.service;

import com.rfidentity.model.SapItem;
import com.rfidentity.model.VmItem;
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
    SAPFileProcessor instance = new SAPFileProcessor();
    private final SapItemService sapItemService;
    private final SAPFileProcessor sapFileProcessor;
    VMFileProcessor instance2 = new VMFileProcessor();
    private final VmItemService vmItemService;
    private final VMFileProcessor vmFileProcessor;
    public void process(Path file) {
        log.info(String.format(
                "Init processing file %s", file.getFileName()));

        try {
            Map<Integer, List<String>> data = sapFileProcessor.readExcel(new File("src/main/resources/SAPVM/SAP_20240414.xlsx"));
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
        try {
            Map<Integer, List<String>> data = VMFileProcessor.readExcel(new File("src/main/resources/SAPVM/VM_20240414.xlsx"));
            data.forEach((rowNum, rowData) -> {
                VmItem vmItem = new VmItem();
                vmItem.setSystemName((rowData.get(0)));
                vmItem.setSystemName((rowData.get(1)));

                vmItemService.save(vmItem);
            });
        }catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
