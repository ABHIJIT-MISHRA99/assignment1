package com.am.assignment1.archive.service;

import com.am.assignment1.dto.AccountDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
@Slf4j
public class AccountFileService {
    public static String Filename = "cusaccount.txt";

    //     public  static String Filename = "/home/abhi/Desktop/cusAccount.txt";
    public void generateFile() throws IOException {
        Path path = Paths.get(Filename);
        if (Files.exists(path)) {
            Files.delete(path);
        }
        Path file = Files.createFile(Paths.get(Filename));
        System.out.println("file:" + file);
    }


    public void writeToFile(List<AccountDTO> accounts) throws IOException {
        Path path = Paths.get(Filename);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            for (AccountDTO account : accounts) {
                bufferedWriter.write(account.print());// this will go in the file.
                bufferedWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("file write failed");

        }

    }
}
