package com.example.restservice.external.documentgenerator.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Random;

@Slf4j
@Service
public class DocumentGenerator implements Generator {
    private static int COUNT = 100000000;
    private static int UPPER_BOUND = 1000000000;

    public void generate(int count) {
        Random random = new Random();

        for (int fileNumber = 0; fileNumber < count; fileNumber++) {
            String filePath = "out" + fileNumber + ".txt";

            File file = new File(filePath);
            try (PrintWriter printWriter = new PrintWriter(new FileWriter(file))) {
                for (int i = 0; i < COUNT; i++) {
                    int randomNumber = random.nextInt(UPPER_BOUND);
                    printWriter.print(randomNumber);
                    printWriter.print(", ");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
