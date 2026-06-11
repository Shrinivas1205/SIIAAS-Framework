package utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static Object[][] getTestData(String sheetName) {

        // Use a List to collect only valid (non-empty) rows
        // This avoids the null-slot problem caused by skipping blank rows
        // in a fixed-size array
        List<Object[]> dataList = new ArrayList<>();

        try {

            FileInputStream fis = new FileInputStream(
                    System.getProperty("user.dir")
                    + "/src/test/resources/testdata/SignupTestData.xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.err.println("[ExcelUtil] Sheet not found: " + sheetName);
                workbook.close();
                fis.close();
                return new Object[0][0];
            }

            int rowCount    = sheet.getPhysicalNumberOfRows();
            int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

            DataFormatter formatter = new DataFormatter();

            // Start from row 1 to skip the header row
            for (int i = 1; i < rowCount; i++) {

                Row row = sheet.getRow(i);

                // Skip physically missing rows
                if (row == null) {
                    continue;
                }

                // Skip rows where the first cell is blank
                String firstCell = formatter.formatCellValue(row.getCell(0));
                if (firstCell == null || firstCell.trim().isEmpty()) {
                    continue;
                }

                // Row is valid — read all columns
                Object[] rowData = new Object[columnCount];

                for (int j = 0; j < columnCount; j++) {

                    Cell cell = row.getCell(j);

                    // getCell() can return null if the cell was never touched
                    rowData[j] = (cell != null)
                            ? formatter.formatCellValue(cell)
                            : "";          // treat missing cell as empty string
                }

                dataList.add(rowData);

                System.out.println("[ExcelUtil] Row " + i + " loaded: "
                        + rowData[0]);   // log the name column for verification
            }

            workbook.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Convert List → Object[][] for TestNG
        return dataList.toArray(new Object[0][0]);
    }
}