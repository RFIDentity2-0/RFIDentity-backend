package com.rfidentity.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "application.file.watch")
public record DirectoryWatcherProperties (
        @NotBlank String sapdirectory,
        @NotBlank String vmdirectory,
        boolean daemon,
        @Positive Long pollInterval,
        @Positive Long quietPeriod
) {}
