package com.rfidentity.service;

import com.rfidentity.model.SapItem;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@Component
public class SAPFileProcessor {

    public Map<Integer, List<String>> readExcel(File file) throws IOException {
        Map<Integer, List<String>> data = new HashMap<>();
        try (
                FileInputStream fis = new FileInputStream(file);
                ReadableWorkbook wb = new ReadableWorkbook(fis)
            ) {
            Sheet sheet = wb.getFirstSheet();
            try (Stream<Row> rows  = sheet.openStream()) {
                rows.skip(1)
                    .forEach(r -> {
                        List<String> rowData = new ArrayList<>();
                        data.put(r.getRowNum(), rowData);
                        AtomicReference<String> column0 = new AtomicReference<>();
                        AtomicReference<String> column1 = new AtomicReference<>();
                        int cellIdx = 0;
                        r.forEach(cell -> {
                            if (cell != null){}
                            int columnIndex = cell.getColumnIndex();
                            String cellValue = cell.getRawValue();

                            if (columnIndex == 0) {

                                if (cell != null){
                                    column0.set(cellValue);

                                }
                                else{
                                    column0.set(null);
                                }
                            } else if (columnIndex == 1) {
                                if (cell != null){
                                    column1.set(cellValue);

                                }
                                else{
                                    column1.set(null);
                                }
                            } if (columnIndex == 0 || columnIndex == 1 || columnIndex == 2 || columnIndex == 3 || columnIndex == 8) {
                                if(cell != null){
                                    rowData.add(cellValue);
                            }
                                else {
                                    rowData.add(null);
                                }
                            }
                        });
                        if (column0.get() != null && column1.get() != null) {
                            rowData.add(column0 + "-" + column1);
                        }
                    });
            }
        }

        return data;
    }
}