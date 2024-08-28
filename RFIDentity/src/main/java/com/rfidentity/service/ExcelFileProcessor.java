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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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
    private long inventory_id;

    public static Date convertExcelDate(int excelDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1900, Calendar.JANUARY, 1);
        calendar.add(Calendar.DAY_OF_YEAR, excelDate - 2);
        return calendar.getTime();
    }

    @Override
    public void process(Path sapFile, Path vmFile) {
        log.info(String.format("Init processing SAP file %s and VM file %s", sapFile.getFileName(), vmFile.getFileName()));

        try {
            if (!sapFile.toFile().exists() || !vmFile.toFile().exists()) {
                log.warn("Pliki SAP lub VM nie zostały znalezione. Inwentaryzacja rozpocznie się, gdy załadujesz pliki.");
                return;
            }

            Map<Integer, List<String>> sapData = sapFileProcessor.readExcel(sapFile.toFile());
            Map<Integer, List<String>> vmData = vmFileProcessor.readExcel(vmFile.toFile());

            Inventory inventory = new Inventory();
            inventory.setDate(LocalDate.now().atStartOfDay());
            inventoryService.save(inventory);
            inventory_id = inventory.getId();
            System.out.println(inventory_id);

            sapData.forEach((rowNum, rowData) -> {
                SapItem sapItem = new SapItem();
                sapItem.setInventoryId(inventory_id);
                sapItem.setDescription(rowData.get(3));
                sapItem.setRoom(rowData.get(4));

                int value = Integer.parseInt(rowData.get(2));
                Date date = convertExcelDate(value);
                SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formatedDate = originalFormat.format(date);
                System.out.println("formatedDate: " + formatedDate);
                sapItem.setCapitalized_date(LocalDate.parse(formatedDate));
                sapItem.setAssetId(rowData.get(5));
                sapItemService.save(sapItem);
            });

            vmData.forEach((rowNum, rowData) -> {
                VmItem vmItem = new VmItem();
                vmItem.setInventoryId(inventory_id);
                vmItem.setAssetId(rowData.get(0));
                vmItem.setSystemName(rowData.get(1));
                vmItem.setDnsName(rowData.get(2));
                vmItem.setType(rowData.get(3));
                vmItem.setManufacturer(rowData.get(4));
                vmItem.setHardwareType(rowData.get(5));
                vmItem.setSerialNo(rowData.get(6));

                int value = Integer.parseInt(rowData.get(7));
                Date date = convertExcelDate(value);
                SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formatedDate = originalFormat.format(date);
                System.out.println("formatedDate: " + formatedDate);
                vmItem.setDateOfInstallation(LocalDate.parse(formatedDate));
                vmItem.setStatus(rowData.get(10));
                vmItem.setDepartment(rowData.get(11));
                vmItem.setPerson_id(rowData.get(14));
                vmItem.setLastName(rowData.get(13));
                vmItem.setFirstName(rowData.get(17));
                vmItem.setLocation(rowData.get(20));
                vmItem.setBuilding(rowData.get(21));
                vmItem.setRoom(rowData.get(22));
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