package com.rfidentity;

import com.rfidentity.service.*;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.file.Path;
import java.time.Duration;


@Configuration
@EnableConfigurationProperties(DirectoryWatcherProperties.class)
@Slf4j
@RequiredArgsConstructor
public class DirectoryWatcherConfig {

    private final DirectoryWatcherProperties properties;
    private final ExcelFileProcessor excelFileProcessor;

    @Bean
    FileSystemWatcher fileSystemWatcher() {
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(
                properties.daemon(),
                Duration.ofMinutes(properties.pollInterval()),
                Duration.ofMinutes(properties.quietPeriod()));
        fileSystemWatcher.addSourceDirectory(Path.of(properties.sapdirectory()).toFile());
        fileSystemWatcher.addSourceDirectory(Path.of(properties.vmdirectory()).toFile());
        fileSystemWatcher.addListener(
                new ExcelFileChangeListener(excelFileProcessor));
        fileSystemWatcher.setTriggerFilter(
                f -> f.toPath().endsWith(".xlsx"));
        fileSystemWatcher.start();

        log.info(String.format(
                "DirectoryWatcherConfig initialized. Monitoring directories %s %s",
                properties.sapdirectory(),
                properties.vmdirectory())
        );

        return fileSystemWatcher;
    }

    @PreDestroy
    public void onDestroy() throws Exception {
        log.info("Shutting Down Directory System Watcher.");
        fileSystemWatcher().stop();
    }

}
