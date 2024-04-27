package com.example.task.dto;

import com.example.task.dto.Animal.Category;
import lombok.Data;

@Data
public class AnimalDocument {
    private String name;
    private String type;
    private String sex;
    private Integer weight;
    private Integer cost;
    public Category category;
}
