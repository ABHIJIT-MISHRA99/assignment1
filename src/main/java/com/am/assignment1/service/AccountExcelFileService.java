package com.am.assignment1.service;

import com.am.assignment1.dto.AccountDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Service
public class AccountExcelFileService {

    public static String Filename="cusaccount.xlsx";
    public void writeExcel(List<AccountDTO> accounts) throws IOException {
        // workbook object
        XSSFWorkbook workbook = new XSSFWorkbook();

        // spreadsheet object
        XSSFSheet spreadsheet
                = workbook.createSheet(" customer account ");

        // creating a row object
        XSSFRow row;

        // This data needs to be written (Object[])
        Map<String, Object[]> Data
                = new TreeMap<String, Object[]>();
        int y=2;
        Data.put("1", new Object[]{"Account Id", "Account name ", "Account Balance ", "Balance Date"});
        for (AccountDTO account:accounts) {
            Data.put(String.valueOf(y), new Object[]{account.getAccountID(),account.getCustomerName(),String.valueOf(account.getAccountBalance()),String.valueOf(account.getCreateDate())});
            y++;
        }

        Set<String> keyid = Data.keySet();

        int rowid = 0;

        // writing the data into the sheets...

        for (String key : keyid) {

            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = Data.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }

        // .xlsx is the format for Excel Sheets...
        // writing the workbook into the file...
//        FileOutputStream out = new FileOutputStream(
//                new File("customeraccountsheet.xlsx"));
        Path path= Paths.get(Filename);
        FileOutputStream out = new FileOutputStream(Filename);
        workbook.write(out);
        out.close();
    }

}
