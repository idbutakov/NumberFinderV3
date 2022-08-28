package com.example.restservice.rest.controller;

import com.example.restservice.rest.service.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class NumberFinderController {
    private final ResultService resultService;

    @GetMapping("/number_finder")
    public ResponseEntity<?> finder(@RequestParam(value = "number", defaultValue = "815407325") Integer number) {
       return resultService.finder(number);
    }
}
