package com.example.task.dto;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Sort;

@Getter
@ToString
public class SortBy {
    private Sort.Direction direction;
    private String fieldName;

    public SortBy(String sortByStringValue) {
        parseSortString(sortByStringValue);
    }
    private void parseSortString(String sortByStringValue) {
        String[] values = sortByStringValue.split(":");
        fieldName = values[0];
        direction = values[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    }
}
