package com.example.restservice.external.numberfinder.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Result {
    private Code code = Code.NOT_FOUND;
    private List<String> fileNames = new ArrayList<String>();
    private String error;

    public Result() {
    }

    public Result(Code code, List<String> fileNames, String error) {
        this.code = code;
        this.fileNames = fileNames;
        this.error = error;
    }
}

