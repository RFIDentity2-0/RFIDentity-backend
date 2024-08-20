package com.rfidentity.api.controller;

import com.rfidentity.service.ExcelFileProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/excel")
@RequiredArgsConstructor
public class ExcelFileController {

    private final ExcelFileProcessor excelFileProcessor;

    @GetMapping("/process")
    public ResponseEntity<Map<String, String>> processExcelFile() {
        Map<String, String> response = new HashMap<>();
        try {
            var filePath = "src/main/resources/SAP_20240414.xlsx";
            excelFileProcessor.process(Paths.get(filePath));
            response.put("status", "success");
            response.put("message", "File processed successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error processing file: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}