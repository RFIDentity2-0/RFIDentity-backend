package com.rfidentity.service;

import com.rfidentity.configuration.DirectoryWatcherConfig;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DirectoryWatcherService {

    private final DirectoryWatcherConfig directoryWatcherConfig;
    private final DataInitializer dataInitializer;
    private boolean isWatching = false;

    public DirectoryWatcherService(DirectoryWatcherConfig directoryWatcherConfig, DataInitializer dataInitializer) {
        this.directoryWatcherConfig = directoryWatcherConfig;
        this.dataInitializer = dataInitializer;
    }

    @Scheduled(fixedDelay = 30000)
    public void ensureMonitoringIsActive() {
        if (!isWatching) {
            directoryWatcherConfig.startWatching();
            isWatching = true;
            System.out.println("Directory watcher is now active");
            dataInitializer.initializeData();
            isWatching = false;
        } else {
            System.out.println("Directory watcher is already active");
        }
    }
}