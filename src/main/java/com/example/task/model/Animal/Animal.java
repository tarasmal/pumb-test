package com.example.task.model.Animal;

import lombok.Data;

@Data
public class Animal {
    private String name;
    private String type;
    private String sex;
    private Integer weight;
    private Integer cost;
    public Category category;

}
