package com.example.restservice.rest.service;

import com.example.restservice.core.CoreProperties;
import com.example.restservice.external.numberfinder.api.Finder;
import com.example.restservice.external.numberfinder.entity.Result;
import com.example.restservice.rest.entity.ResultEntity;
import com.example.restservice.rest.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class ResultService {
    private final ResultRepository resultRepository;
    private final Finder finder;
    private final CoreProperties coreProperties;

    public ResponseEntity<?> finder(Integer number){
        try {
            Result result = finder.findNumber(number, coreProperties.getCountFile());
            ResultEntity resultEntity = new ResultEntity(result, number);
            resultRepository.save(resultEntity);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Нет файлов");
        }
    }
}
