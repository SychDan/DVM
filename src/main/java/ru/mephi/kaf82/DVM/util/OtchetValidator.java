package ru.mephi.kaf82.DVM.util;


import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.mephi.kaf82.DVM.model.Otchet;

import java.util.Arrays;

@Service
public class OtchetValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Otchet.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Otchet otchet = (Otchet) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","NotEmpty.otchetForm.name");

//        if(otchet.getMedia().getFile()!=null && otchet.getId() == 0){
//            if (otchet.getMedia().getFile().getSize() == 0) {
//                errors.rejectValue("media.file", "missing.media");
//            }
//        }
//
//        if(otchet.getPhoto().getFile()!=null && otchet.getId() == 0){
//            if (otchet.getPhoto().getFile().getSize() == 0) {
//                errors.rejectValue("photo.file", "missing.file");
//            }
//        }
//
//        String[] suffixPhoto = {".png", ".jpg", ".jpeg"};
//        if (otchet.getPhoto().getFile() != null && !Arrays.stream(suffixPhoto).anyMatch(e -> otchet.getPhoto().getFile().getOriginalFilename().endsWith(e))) {
//            errors.rejectValue("photo.file", "unsupportedFormat.file");
//        }
//
//        String[] suffixMedia = {".mp4", ".avi", ".mkv"};
//        if (otchet.getPhoto().getFile() != null && !Arrays.stream(suffixMedia).anyMatch(e -> otchet.getMedia().getFile().getOriginalFilename().endsWith(e))) {
//            errors.rejectValue("media.file", "unsupportedFormat.file");
//        }
    }
}
