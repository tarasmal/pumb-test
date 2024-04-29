package com.example.task.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@Getter
@RequiredArgsConstructor
@ToString
public class Filter {
    private final String fieldName;
    private final String value;
}
