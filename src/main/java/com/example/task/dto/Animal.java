package com.example.task.dto;
import lombok.Data;

@Data
public class Animal{
    private String name;
    private String type;
    private String sex;
    private Integer weight;
    private Integer cost;
    public Integer determineCategory(){
        if (cost <= 20) {
            return Category.FIRST.getValue();
        }
        else if (cost <= 40 ) {
            return Category.SECOND.getValue();
        }
        else if (cost <= 60) {
            return Category.THIRD.getValue();
        }
        else {
            return Category.FOURTH.getValue();
        }
    }
}
