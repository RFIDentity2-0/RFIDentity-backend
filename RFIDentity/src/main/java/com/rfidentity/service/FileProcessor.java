package com.rfidentity.service;

import java.nio.file.Path;


public interface FileProcessor {

    void process(Path file, Path file2);

    void process(Path file);

}
