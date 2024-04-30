package com.example.task.validator;

import com.example.task.dto.Animal;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AnimalValidator implements Validator {

    private static final String FIELD_IS_REQUIRED = "The %s field is required.";
    private static final String FIELD_MUST_BE_POSITIVE_OR_ZERO = "The %s field must be positive";

    @Override
    public boolean supports(Class<?> clazz) {
        return Animal.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Animal animal = (Animal) target;
        validateField(animal.getName(), "name", errors);
        validateField(animal.getType(), "type", errors);
        validateField(animal.getSex(), "sex", errors);
        validateField(animal.getWeight(), "weight", errors);
        validateField(animal.getCost(), "cost", errors);
    }

    private void validateField(String value, String fieldName, Errors errors) {
        if (value == null || value.isEmpty()) {
            errors.rejectValue(fieldName, fieldName + ".required", FIELD_IS_REQUIRED.formatted(fieldName));
        }
    }
    private void validateField(Double value, String fieldName, Errors errors) {
        if (value <= 0) {
            errors.rejectValue(fieldName, fieldName + ".must_be_positive", FIELD_MUST_BE_POSITIVE_OR_ZERO.formatted(fieldName));
        }
    }
}
