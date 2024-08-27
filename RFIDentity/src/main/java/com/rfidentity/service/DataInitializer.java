package com.rfidentity.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final SapItemService sapItemService;
    private final ExcelFileProcessor excelFileProcessor;

    @PostConstruct
    public void initializeData() {

        log.info("The database is empty, starting to load data from the Excel file.");

        var filePath = Paths.get("src/main/resources/SAPVM/SAP.xlsx");
        var filePath2 = Paths.get("src/main/resources/SAPVM/VM.xlsx");

        Path oldFileDirectory = Paths.get("src/main/resources/SAPVM/oldfile");
        if (Files.notExists(oldFileDirectory)) {
            try {
                Files.createDirectory(oldFileDirectory);
            } catch (IOException e) {
                log.error("Failed to create oldfile directory: " + e.getMessage(), e);
                return;
            }
        }

        try {
            excelFileProcessor.process(filePath, filePath2);

            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            String formattedDateTime = localDateTime.format(formatter);

            if (Files.exists(filePath)) {
                Files.move(filePath, filePath.resolveSibling("oldfile/SAPOLD_" + formattedDateTime + ".xlsx"));
            } else {
                log.warn("File not found: {}", filePath);
            }

            if (Files.exists(filePath2)) {
                Files.move(filePath2, filePath2.resolveSibling("oldfile/VMOLD_" + formattedDateTime + ".xlsx"));
            } else {
                log.warn("File not found: {}", filePath2);
            }

            log.info("Data has been successfully loaded into the database.");

        } catch (Exception e) {
            log.error("Error processing files: " + e.getMessage(), e);
        }

    }

}