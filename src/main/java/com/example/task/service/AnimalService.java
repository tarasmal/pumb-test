package com.example.task.service;

import com.example.task.dto.Animal.Animal;
import com.example.task.dto.AnimalDocument;
import com.example.task.mapper.AnimalMapper;
import com.example.task.validator.AnimalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalValidator animalValidator;
    private final MongoTemplate mongoTemplate;
    private final AnimalMapper animalMapper;

    public String saveToDb(List<Animal> animals) {
        ArrayList<String> errors = new ArrayList<>();
        for (Animal animal : animals) {
            BindingResult result = new BeanPropertyBindingResult(animal, "animal");
            animalValidator.validate(animal, result);
            if (!result.hasErrors()){
                AnimalDocument animalDocument = animalMapper.toAnimalDocument(animal);
                mongoTemplate.save(animalDocument, "animals");
            }
            else {
                errors.add(result.getAllErrors().toString());
            }
        }

        return errors.isEmpty() ? "" : Arrays.deepToString(errors.toArray());
    }
}
