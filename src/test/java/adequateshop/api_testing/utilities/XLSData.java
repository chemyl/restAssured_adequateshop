package adequateshop.api_testing.utilities;

import adequateshop.api_testing.payloads.CustomerPOJO;
import com.github.javafaker.Faker;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XLSData {
    static Faker faker = new Faker();

    @DataProvider(name = "customerData")
    public static Object[][] readAllData() throws IOException {
        FileInputStream fis = new FileInputStream("src/test/resources/testCasesData.xls");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1);

            for (int j = 0; j < columnCount; j++) {
                Cell cell = row.getCell(j);
                data[i][j] = cell.toString();
            }
        }

        workbook.close();
        fis.close();
        return data;
    }

    public static List<CustomerPOJO> convertExcelToPojo() throws IOException {
        FileInputStream fis = new FileInputStream("src/test/resources/testCasesData.xls");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

        List<CustomerPOJO> pojoList = new ArrayList<>();

        for (Row row : sheet) {
            // Skip the header row
            if (row.getRowNum() == 0) {
                continue;
            }

            // Create a new instance of CustomerPOJO class
            CustomerPOJO pojo = new CustomerPOJO();
            pojo.setId(faker.idNumber().hashCode());
            pojo.setName(row.getCell(1).getStringCellValue());
            pojo.setEmail(row.getCell(2).getStringCellValue());
            pojo.setLocation(row.getCell(3).getStringCellValue());

            // Add the POJO to the list
            pojoList.add(pojo);
        }

        workbook.close();
        fis.close();

        return pojoList;
    }
}