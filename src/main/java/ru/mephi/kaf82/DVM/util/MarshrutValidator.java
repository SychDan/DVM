package ru.mephi.kaf82.DVM.util;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.mephi.kaf82.DVM.model.Marshrut;

@Service
public class MarshrutValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Marshrut.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","NotEmpty.marshrutForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"description","NotEmpty.marshrutForm.description");
    }
}
