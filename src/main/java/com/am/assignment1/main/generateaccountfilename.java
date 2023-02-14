package com.am.assignment1.main;

import com.am.assignment1.service.accountexcelfileservice;
import com.am.assignment1.service.accountfileservice;
import com.am.assignment1.service.customernamegeneratorservice;
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
public class generateaccountfilename {

    @Autowired
    private accountfileservice accountfileservice;
    @Autowired
    private accountexcelfileservice accountexcelfileservice;
    @Autowired
    private customernamegeneratorservice customernamegeneratorservice;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static List<String> acclist;
    public  static Random rd = new Random();


    @PostConstruct
    public void createAccountFile() throws IOException {

        List<List<String>> l = new ArrayList<>();
        accountfileservice.generateFile();
        for (int i = 0; i < 5; i++) {
            List<String> acclist = new ArrayList<>();
            // name
            String generateName = customernamegeneratorservice.generateName();
            // account id
            String accountId = String.format("%012d", i);
            // account balance

            float input = rd.nextFloat();
            float balance = Float.parseFloat(df.format(input));
            // create date
            LocalDate n = LocalDate.now();
            acclist.add(generateName);
            acclist.add(accountId);
            acclist.add(String.valueOf(balance));
            acclist.add(String.valueOf(n));



            l.add(acclist);
            writlefile(l);
            writeexcel(l,i);
        }
    }

    private void writeexcel(List<List<String>> l, int i) throws IOException {
        List<List<String>> k=l;
       accountexcelfileservice.writeExcel(k,i);
    }

    private void writlefile(List<List<String>> acclist) throws IOException {
        List<List<String>> k = acclist;

        accountfileservice.writeAccount(k);

    }
}
