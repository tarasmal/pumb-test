package com.example.task.validator;

import com.example.task.dto.Animal.Animal;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AnimalValidator implements Validator {

    private static final String FIELD_IS_REQUIRED = "The %s field is required.";

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

    private void validateField(Object value, String fieldName, Errors errors) {
        if (value == null || (value instanceof String && ((String) value).isEmpty())) {
            errors.rejectValue(fieldName, fieldName + ".required", FIELD_IS_REQUIRED.formatted(fieldName));
        }
    }
}
