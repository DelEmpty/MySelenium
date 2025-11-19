package utils;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
public class ExcelUtils {

    public static List<String> getTestsFromExcel(String filePath, String sheetName) throws IOException {

        List<String> testsToRun = new ArrayList<>();

        try(FileInputStream fis = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            for (Row row : sheet) {
                Cell testIdCell = row.getCell(0);
                Cell runFlagCell = row.getCell(1);

                if (testIdCell != null && runFlagCell != null && runFlagCell.getStringCellValue().equalsIgnoreCase("Y")) {
                    testsToRun.add(testIdCell.getStringCellValue());
                }
            }

        } catch (IOException e) {
            log.info("Failed to read Excel file:");
        }
        return testsToRun;
    }
}
