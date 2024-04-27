package com.example.task.dto.Animal;
import lombok.Data;

@Data
public class Animal{
    private String name;
    private String type;
    private String sex;
    private String weight;
    private int cost;
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
