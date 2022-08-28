package com.example.restservice.external.numberfinder.api;

import com.example.restservice.core.CoreProperties;
import com.example.restservice.external.numberfinder.entity.Code;
import com.example.restservice.external.numberfinder.entity.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Primary
public class NumberFinder implements Finder {
    private final CoreProperties coreProperties;

    public Result findNumber(int input, int countFiles) throws InterruptedException, ExecutionException {
        Result result = new Result();
        ExecutorService executor = Executors.newFixedThreadPool(countFiles);
        List<Callable<Set<Integer>>> callableTasks = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int fileNumber = 0; fileNumber < countFiles; fileNumber++) {
            int finalFileNumber = fileNumber;
            Callable<Set<Integer>> callableTask = () -> find(finalFileNumber, input);
            callableTasks.add(callableTask);
        }
        List<Future<Set<Integer>>> futures = executor.invokeAll(callableTasks);
        int i = 0;
        for (Future<Set<Integer>> future : futures) {
            if (future.get().contains(input)) {
                result.setCode(Code.OK);
                result.getFileNames().add("out" + i + ".txt");
            }
            i++;
        }
        System.out.println((System.currentTimeMillis() - start) / 1000);
        return result;
    }

    private Set<Integer> find(int fileNumber, int findNumber) {
        String fileName = String.format("%s%d.%s", coreProperties.getPath(), fileNumber, coreProperties.getExtension());
        File file = new File(fileName);

        Set<Integer> integerSet = new HashSet<>();
        try(Scanner scanner = new Scanner(file)){
            log.info("Открыли файл " + fileName);
            scanner.useDelimiter(", ");
            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number == findNumber) {
                    integerSet.add(number);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return integerSet;
    }
}
