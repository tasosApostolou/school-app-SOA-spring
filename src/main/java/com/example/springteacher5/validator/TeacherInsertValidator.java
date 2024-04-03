package com.example.springteacher5.validator;

import com.example.springteacher5.dto.teacherDTOs.TeacherInsertDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TeacherInsertValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return TeacherInsertDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        TeacherInsertDTO teachereInsertDTO = (TeacherInsertDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstname","empty");
        if(teachereInsertDTO.getFirstname().length()< 3 || teachereInsertDTO.getFirstname().length() > 50){
            errors.reject("firstname", "size");

        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastname","empty");
        if(teachereInsertDTO.getLastname().length()< 3 || teachereInsertDTO.getLastname().length() > 50){
            errors.reject("lastname", "size");
        }


    }
}
