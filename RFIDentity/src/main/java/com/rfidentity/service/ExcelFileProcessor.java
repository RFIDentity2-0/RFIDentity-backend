package com.rfidentity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.nio.file.Path;


@Component
@Slf4j
@RequiredArgsConstructor
public class ExcelFileProcessor implements FileProcessor {

    private final SapItemService sapItemService;

    public void process(Path file) {
        log.info(String.format(
                "Init processing file %s", file.getFileName()));
    }

}
