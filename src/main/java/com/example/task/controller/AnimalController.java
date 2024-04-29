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
import java.util.Set;

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
        Set<String> validFilters = Set.of("type", "category", "sex");
        AnimalQueryBuilder queryBuilder = new AnimalQueryBuilder(validFilters, sortBy, filters);
        List<AnimalDocument> animals = animalQueryService.getAnimals(queryBuilder.buildQuery());
        return ResponseEntity.ok().body(animals);

    }
}
