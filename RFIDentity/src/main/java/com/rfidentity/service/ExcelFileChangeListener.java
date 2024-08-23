package com.rfidentity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExcelFileChangeListener implements FileChangeListener {

    private final ExcelFileProcessor excelFileProcessor;
    private final SapItemService sapItemService;

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        for (ChangedFiles changedFiles : changeSet) {
            changedFiles.getFiles().stream()
                    .filter(f -> f.getFile().getName().endsWith(".xlsx"))
                    .forEach(f -> {
                        log.info("Detected change in file: " + f.getFile().getAbsolutePath());
                        try {
                            excelFileProcessor.process(f.getFile().toPath());
                            log.info("Data has been successfully updated from file: " + f.getFile().getAbsolutePath());
                        } catch (Exception e) {
                            log.error("Error processing file: " + f.getFile().getAbsolutePath(), e);
                        }
                    });
        }
    }
}