package com.rfidentity.service;

import com.rfidentity.configuration.DirectoryWatcherConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

        log.info("Starting to load data from the Excel files.");

        Path directory = Paths.get("Test/SAPVM/");

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


            excelFileProcessor.process(sapFilePath.get(), vmFilePath.get());

            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            String formattedDateTime = localDateTime.format(formatter);

            Files.move(sapFilePath.get(), Paths.get("Test/").resolveSibling("Test/oldfile/SAPOLD_" + formattedDateTime + ".xlsx"));
            Files.move(vmFilePath.get(),  Paths.get("Test/").resolveSibling("Test/oldfile/VMOLD_" + formattedDateTime + ".xlsx"));

        } catch (Exception e) {
            log.error("Error processing files: " + e.getMessage(), e);
        }
    }
}
