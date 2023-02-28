package com.am.assignment1.service;


import com.am.assignment1.dto.AccountDTO;
import jakarta.annotation.PostConstruct;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountDataGen {

    @Value("${account_upload_service_url}")
    private String accountUploadServiceUrl;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @PostConstruct
    void accGen() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("account_data.txt").getFile());
        System.out.println("read file :" + file.toPath());
        if (file.exists()) {
            System.out.println("found file ");
        } else {
            System.out.println("file missing ");
        }
        List<AccountDTO> accounts = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(file.toPath())) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                AccountDTO accountDTO = new AccountDTO(split[0], split[1], Float.parseFloat(split[2]), LocalDate.now());
                accounts.add(accountDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of("d:/accgen.txt"))) {
            accounts.forEach(x ->
            {
                try {
                    bufferedWriter.write(x.print());
                    bufferedWriter.write(System.lineSeparator());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        uploadFile();

    }

    private void uploadFile() {
        System.out.println("uploading file");
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:9001/accounts/file").build().toUri();

        WebClient webClient = webClientBuilder.build();

        String block = webClient.post()
                .uri(uri)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromMultipartData(fromFile(new File("D:/accgen.txt"))))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(block);

    }

    public MultiValueMap<String, HttpEntity<?>> fromFile(File file) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(file.toPath())) {
            if (file.exists()) {
                String line;
                while ( (line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("uploadfile", new FileSystemResource(file));
        return builder.build();
    }

}
