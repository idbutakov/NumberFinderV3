package com.example.restservice.external.numberfinder.api;

import com.example.restservice.external.numberfinder.entity.Result;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface Finder {
    Result findNumber(int input, int countFiles) throws IOException, InterruptedException, ExecutionException;
}
