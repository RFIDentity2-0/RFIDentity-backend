package com.rfidentity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExcelFileChangeListener implements FileChangeListener {

    private final ExcelFileProcessor excelFileProcessor;
    private final SapItemService sapItemService;

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        boolean sapFileDetected = false;
        boolean vmFileDetected = false;

        for (ChangedFiles changedFiles : changeSet) {
            for (var file : changedFiles.getFiles()) {
                if (file.getFile().getName().endsWith(".xlsx")) {
                    if (file.getFile().getAbsolutePath().contains("SAP")) {
                        sapFileDetected = true;
                    } else if (file.getFile().getAbsolutePath().contains("VM")) {
                        vmFileDetected = true;
                    }
                }
            }
        }

        if (sapFileDetected && vmFileDetected) {
            log.info("Both SAP and VM files detected. Processing files.");

            try {
                // Przetwórz oba pliki
                var sapFilePath = Path.of("src/main/resources/SAP_20240414.xlsx");
                var vmFilePath = Path.of("src/main/resources/VM_20240414.xlsx");

                excelFileProcessor.process(sapFilePath);
                excelFileProcessor.process(vmFilePath);

                log.info("Data has been successfully updated from both SAP and VM files.");
            } catch (Exception e) {
                log.error("Error processing files: ", e);
            }
        } else {
            log.info("Required files not detected. No data update performed.");
        }
    }
}