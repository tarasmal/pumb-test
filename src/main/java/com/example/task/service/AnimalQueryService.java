package com.example.task.service;

import com.example.task.dto.AnimalDocument;
import com.example.task.dto.Filter;
import com.example.task.dto.SortBy;
import com.example.task.util.AnimalQueryBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalQueryService {
    private final MongoTemplate mongoTemplate;
    public List<AnimalDocument> getAnimals(Query query) {
        return mongoTemplate.find(query, AnimalDocument.class, "animals");
    }


}

