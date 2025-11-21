package com.example.nth_min_service.util;

import lombok.extern.apachecommons.CommonsLog;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class XlsxReader {
    public List<Integer> read(String path) {
        List<Integer> result = new ArrayList<>();

        try (FileInputStream in = new FileInputStream(path);
             Workbook wb = new XSSFWorkbook(in)) {

            Sheet sheet = wb.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell == null) continue;

                switch (cell.getCellType()) {
                    case NUMERIC -> result.add((int) cell.getNumericCellValue());
                    case STRING -> {
                        try {
                            result.add(Integer.parseInt(cell.getStringCellValue().trim()));
                        } catch (Exception ignored) {}
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения файла: " + e.getMessage(), e);
        }

        return result;
    }
}
