package com.example.task.dto;
import lombok.Data;

@Data
public class Animal{
    private String name;
    private String type;
    private String sex;
    private Integer weight;
    private Integer cost;
    public Category determineCategory(){
        if (cost <= 20) {
            return Category.FIRST;
        }
        else if (cost <= 40 ) {
            return Category.SECOND;
        }
        else if (cost <= 60) {
            return Category.THIRD;
        }
        else {
            return Category.FOURTH;
        }
    }
}
