package com.rfidentity.service;

import com.rfidentity.model.SapItem;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.Sheet;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class VMFileProcessor {
    public static Map<Integer, List<String>> readExcel(File file) throws IOException {
        Map<Integer, List<String>> data = new HashMap<>();
        try (
                FileInputStream fis = new FileInputStream(file);
                ReadableWorkbook wb = new ReadableWorkbook(fis)
        ) {
            Sheet sheet = wb.getFirstSheet();

            try (Stream<Row> rows = sheet.openStream()) {
                rows.skip(1)
                        .forEach(r -> {
                            List<String> rowData = new ArrayList<>();
                            data.put(r.getRowNum(), rowData);
                            r.forEach(cell -> {
                                if (cell != null) {
                                    int columnIndex = cell.getColumnIndex();
                                    String cellValue = cell.getRawValue();

                                    if (columnIndex ==0 ||columnIndex == 1 || columnIndex == 2 || columnIndex == 3 || columnIndex == 4 || columnIndex == 5 || columnIndex == 6 || columnIndex == 10 || columnIndex == 11 || columnIndex == 12) {
                                        rowData.add(cellValue);
                                    }
                                }
                            });
                        });
            }
        }
        return data;
    }
}
