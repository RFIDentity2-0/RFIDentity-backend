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

    private final FileProcessor fileProcessor;

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        for (ChangedFiles changedFiles : changeSet) {
            changedFiles.getFiles().stream()
                    .forEach(f -> System.out.println("Got " + f.getFile().getAbsolutePath()));
        }
    }
}
