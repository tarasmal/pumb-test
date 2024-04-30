package com.example.task.controller;

import com.example.task.dto.AnimalDocument;
import com.example.task.service.AnimalQueryService;
import com.example.task.util.AnimalQueryBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalQueryService animalQueryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AnimalDocument>> getAnimals(
            @RequestParam MultiValueMap<String, String> filters,
            @RequestParam(required = false) List<String> sortBy
    ){
        AnimalQueryBuilder queryBuilder = new AnimalQueryBuilder(sortBy, filters);
        List<AnimalDocument> animals = animalQueryService.getAnimals(queryBuilder.buildQuery());
        return ResponseEntity.ok().body(animals);

    }
}
