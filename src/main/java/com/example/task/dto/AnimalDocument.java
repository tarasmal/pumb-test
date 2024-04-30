package com.example.task.dto;

import lombok.Data;

@Data
public class AnimalDocument {
    private String name;
    private String type;
    private String sex;
    private Double weight;
    private Double cost;
    public Integer category;
}
