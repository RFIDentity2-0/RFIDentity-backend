package com.rfidentity.service;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


class SAPFileProcessorTest {

    @Test
    void readExcel() {
        SAPFileProcessor instance = new SAPFileProcessor();
        File f = new File(".");
        String absolutePath = f.getAbsolutePath();
        try {
            Map<Integer, List<String>> dataFromExcel = instance.readExcel(new File("src/test/resources/SAP_20240414.xlsx"));
            System.out.println(dataFromExcel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
