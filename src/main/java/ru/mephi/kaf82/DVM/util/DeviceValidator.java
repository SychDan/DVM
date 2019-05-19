package ru.mephi.kaf82.DVM.util;


import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.mephi.kaf82.DVM.model.Camera;

@Service
public class DeviceValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Camera.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"camCode","NotEmpty.cameraForm.camCode");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"serNum","NotEmpty.cameraForm.serNum");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"invNum","NotEmpty.cameraForm.invNum");
    }
}
