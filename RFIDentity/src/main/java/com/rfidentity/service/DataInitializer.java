package com.rfidentity.service;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;





@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final SapItemService sapItemService;
    private final ExcelFileProcessor excelFileProcessor;

    @PostConstruct
    public void initializeData() {
        log.info("Starting data loading from Excel file.");

        try {
            var filePath = Paths.get("src/main/resources/SAPVM/VM_20240414");
            var filePath2 = Paths.get("src/main/resources/SAPVM/SAP_20240414");
            excelFileProcessor.process(filePath);
            excelFileProcessor.process(filePath2);
            log.info("Data has been successfully loaded into the database.");
        } catch (Exception e) {
            log.error("Error processing file: " + e.getMessage(), e);
        }
    }
}

