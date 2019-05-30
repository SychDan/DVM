package ru.mephi.kaf82.DVM.util;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.mephi.kaf82.DVM.model.Person;
import ru.mephi.kaf82.DVM.repository.PersonRepository;

import javax.annotation.Resource;
import java.util.Arrays;


@Service
public class PersonValidator implements Validator {

    @Resource
    private PersonRepository personRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"family","NotEmpty.personForm.family");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName","NotEmpty.personForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"secondName","NotEmpty.personForm.secondName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"cardNumber","NotEmpty.personForm.cardNumber");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"access","NotEmpty.personForm.access");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"admin","NotEmpty.personForm.admin");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"status","NotEmpty.personForm.status");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"pass","NotEmpty.personForm.pass");

//        if (personRepository.findByCardNumber(person.getCardNumber()) != null) {
//            errors.rejectValue("cardNumber", "Duplicate.personForm.cardNumber");
//        }

        if(person.getPhoto().getFile()!=null && person.getId() == 0){
            if (person.getPhoto().getFile().getSize() == 0) {
                errors.rejectValue("photo.file", "missing.file");
            }
        }

        String[] suffix = {".png", ".jpg", ".jpeg"};
        if (person.getPhoto().getFile() != null && !Arrays.stream(suffix).anyMatch(e -> person.getPhoto().getFile().getOriginalFilename().endsWith(e))) {
            errors.rejectValue("photo.file", "unsupportedFormat.file");
        }
    }
}
