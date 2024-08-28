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
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final ExcelFileProcessor excelFileProcessor;

    @PostConstruct
    public void initializeData() {

        log.info("The database is empty, starting to load data from the Excel files.");

        Path directory = Paths.get("C:/Network_file");

        try (Stream<Path> paths = Files.walk(directory)) {
            Optional<Path> sapFilePath = paths
                    .filter(path -> path.getFileName().toString().startsWith("SAP") && path.getFileName().toString().endsWith(".xlsx"))
                    .findFirst();

            Optional<Path> vmFilePath = Files.walk(directory)
                    .filter(path -> path.getFileName().toString().startsWith("VM") && path.getFileName().toString().endsWith(".xlsx"))
                    .findFirst();

            if (sapFilePath.isEmpty() || vmFilePath.isEmpty()) {
                log.warn("SAP or VM file not found.");
                return;
            }

            Path oldFileDirectory = Paths.get("C:/Users/ext1/Documents/GitHub/RFIDentity-backend/RFIDentity/src/main/resources/SAPVM/oldfile");
            if (Files.notExists(oldFileDirectory)) {
                try {
                    Files.createDirectory(oldFileDirectory);
                } catch (IOException e) {
                    log.error("Failed to create oldfile directory: " + e.getMessage(), e);
                    return;
                }
            }

            excelFileProcessor.process(sapFilePath.get(), vmFilePath.get());

            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            String formattedDateTime = localDateTime.format(formatter);

            Files.move(sapFilePath.get(), sapFilePath.get().resolveSibling("C:/Users/ext1/Documents/GitHub/RFIDentity-backend/RFIDentity/src/main/resources/SAPVM/oldfile/SAPOLD_" + formattedDateTime + ".xlsx"));
            Files.move(vmFilePath.get(), vmFilePath.get().resolveSibling("C:/Users/ext1/Documents/GitHub/RFIDentity-backend/RFIDentity/src/main/resources/SAPVM/oldfile/VMOLD_" + formattedDateTime + ".xlsx"));

        } catch (Exception e) {
            log.error("Error processing files: " + e.getMessage(), e);
        }
    }
}