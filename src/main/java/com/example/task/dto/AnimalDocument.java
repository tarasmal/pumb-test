package com.example.task.dto;

import lombok.Data;

@Data
public class AnimalDocument {
    private String name;
    private String type;
    private String sex;
    private Integer weight;
    private Integer cost;
    public Integer category;
}
