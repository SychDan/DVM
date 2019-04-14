package ru.mephi.kaf82.DVM.util;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.mephi.kaf82.DVM.model.Terminal;

@Service
public class TeminalValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Terminal.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Terminal terminal = (Terminal) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"nameGroup","NotEmpty.terminalForm.nameGroup");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"termNumber","NotEmpty.terminalForm.termNumber");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"ip","NotEmpty.terminalForm.ip");
        ValidationUtils.rejectIfEmpty(errors,"address","NotEmpty.terminalForm.address");


    }
}
