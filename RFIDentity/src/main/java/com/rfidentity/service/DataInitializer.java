package com.rfidentity.service;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;





@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final SapItemService sapItemService;
    private final ExcelFileProcessor excelFileProcessor;

    @PostConstruct
    public void initializeData() {

        log.info("The database is empty, starting to load data from the Excel file.");

        try {
            File f = new File(".");
            String absolutePath = f.getAbsolutePath();
            Path sapFilePath = Paths.get("./RFIDentity/src/main/resources/SAPVM/Case1/SAP_SAMPLE01.xlsx");
            Path vmFilePath = Paths.get("./RFIDentity/src/main/resources/SAPVM/Case1/VM_SAMPLE01.xlsx");

            if (sapFilePath.toFile().exists()
                    && sapFilePath.toFile().exists()) {

                excelFileProcessor.process(sapFilePath, vmFilePath);
                log.info("Data has been successfully loaded into the database.");
            } else {
                log.info("Sap file of VM file doesn't exist, skipping data initialization.");
            }

        } catch (Exception e) {
            log.error("Error processing file: " + e.getMessage(), e);
        }

    }
}
