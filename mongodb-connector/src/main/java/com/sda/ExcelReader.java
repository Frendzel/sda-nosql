package com.sda;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {

    public void readFile() throws IOException, InvalidFormatException {
        try (Workbook sheets = WorkbookFactory.create(
                new File("/Users/marcinerbel/Documents/sda/sda-nosql/mongodb-connector/src/main/resources/result_2.xlsx"))) {
            System.out.println(sheets);
            Sheet sheetAt = sheets.getSheetAt(0);
            Row row = sheetAt.getRow(0);
            for (Cell cell : row) {
                System.out.println(cell);
            }
            Iterator<Row> rowIterator = sheetAt.rowIterator();
            rowIterator.forEachRemaining(cells -> cells.forEach(System.out::println));
            Grade grade = new Grade(1, "cos", "cos");
            JSONObject jsonObject = new JSONObject(grade);
            System.out.println(jsonObject);
        }

    }


    public static void main(String[] args) {
        ExcelReader excelReader = new ExcelReader();
        try {
            excelReader.readFile();
        } catch (IOException | InvalidFormatException e) {
            System.out.println("ups :(" + e.getMessage());
        }
    }
}
