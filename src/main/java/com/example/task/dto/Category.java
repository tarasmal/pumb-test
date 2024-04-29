package com.example.task.dto;

import lombok.Getter;

@Getter
public enum Category {
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4);

    private final int value;

    Category(int value) {
        this.value = value;
    }

}
