package com.rfidentity.configuration;

import com.rfidentity.service.ExcelFileChangeListener;
import com.rfidentity.service.ExcelFileProcessor;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@Configuration
@EnableConfigurationProperties(DirectoryWatcherProperties.class)
@Slf4j
@RequiredArgsConstructor
public class DirectoryWatcherConfig {

    private final DirectoryWatcherProperties properties;
    private final ExcelFileProcessor excelFileProcessor;
    private FileSystemWatcher fileSystemWatcher;
    private volatile boolean watching = false;

    @PostConstruct
    public void startWatching() {
        if (!watching) {
            fileSystemWatcher = new FileSystemWatcher(
                    properties.daemon(),
                    Duration.ofMinutes(properties.pollInterval()),
                    Duration.ofMinutes(properties.quietPeriod()));
            fileSystemWatcher.addSourceDirectory(Path.of(properties.sapdirectory()).toFile());
            fileSystemWatcher.addSourceDirectory(Path.of(properties.vmdirectory()).toFile());
            fileSystemWatcher.addListener(new ExcelFileChangeListener(excelFileProcessor));
            fileSystemWatcher.setTriggerFilter(f -> f.toPath().endsWith(".xlsx"));
            fileSystemWatcher.start();
            watching = true;
            log.info("DirectoryWatcherConfig initialized. Monitoring directories: {} and {}",
                    properties.sapdirectory(),
                    properties.vmdirectory());
        }
    }

    @PreDestroy
    public void onDestroy() throws Exception {
        log.info("Shutting Down Directory System Watcher.");
        if (fileSystemWatcher != null) {
            fileSystemWatcher.stop();
        }
        watching = false;
    }


}