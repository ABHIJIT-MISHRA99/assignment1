package com.am.assignment1.main;

import com.am.assignment1.dto.AccountDTO;
import com.am.assignment1.service.AccountExcelFileService;
import com.am.assignment1.service.AccountFileService;
import com.am.assignment1.service.CustomerNameGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GenerateAccountFilename {

    @Autowired
    private AccountFileService accountfileservice;
    @Autowired
    private AccountExcelFileService accountexcelfileservice;
    @Autowired
    private CustomerNameGeneratorService customernamegeneratorservice;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static List<String> acclist;
    public  static Random rd = new Random();


    @PostConstruct
    public void createAccountFile() throws IOException {
        List<AccountDTO> accounts = new ArrayList<>();
        accountfileservice.generateFile();
        for (int i = 0; i < 5; i++) {
            // name
            String generateName = customernamegeneratorservice.generateName();
            // account id
            String accountId = String.format("%012d", i);
            // account balance
            float input = rd.nextFloat();
            float balance = Float.parseFloat(df.format(input));
            // create date
            LocalDate dateNow = LocalDate.now();
            AccountDTO accountDTO = new AccountDTO(generateName, accountId, balance, dateNow);
            accounts.add(accountDTO);

        }


        accountfileservice.writeToFile(accounts);
        accountexcelfileservice.writeExcel(accounts);

    }
    @PostConstruct
    public void createAccountExcelFile() throws IOException {
        List<AccountDTO> accounts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // name
            String generateName = customernamegeneratorservice.generateName();
            // account id
            String accountId = String.format("%012d", i);
            // account balance
            float input = rd.nextFloat();
            float balance = Float.parseFloat(df.format(input));
            // create date
            LocalDate dateNow = LocalDate.now();
            AccountDTO accountDTO = new AccountDTO(generateName, accountId, balance, dateNow);
            accounts.add(accountDTO);

        }

        accountexcelfileservice.writeExcel(accounts);

    }



}
