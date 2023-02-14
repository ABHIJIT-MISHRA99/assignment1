package com.am.assignment1.service;

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
public class AccountFileService {
    public static String Filename="cusaccount.txt";

    //     public  static String Filename = "/home/abhi/Desktop/cusAccount.txt";
    public void generateFile() throws IOException {
        Path path = Paths.get(Filename);
        if ( Files.exists(path)){
            Files.delete(path);
        }
        Path file = Files.createFile(Paths.get(Filename));
        System.out.println("file:"+file);
    }
    public void writeAccount(List<List<String>> acc) throws IOException {
        Path path = Paths.get(Filename);
        try(BufferedWriter bufferedWriter=Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND)){
            for(List<String> i:acc) {
                bufferedWriter.write(i.toString());
                bufferedWriter.write(System.lineSeparator());
            }
        }catch (IOException e){
            e.printStackTrace();
            System.err.println("file write failed");

        }

    }
}
