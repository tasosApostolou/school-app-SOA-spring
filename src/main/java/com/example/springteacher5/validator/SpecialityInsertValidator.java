package com.example.springteacher5.validator;

import com.example.springteacher5.dto.specialitiesDTO.SpecialityInsertDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SpecialityInsertValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SpecialityInsertDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SpecialityInsertDTO specialityInsertDTO = (SpecialityInsertDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"speciality","empty");
        if(specialityInsertDTO.getSpeciality().length()< 3 || specialityInsertDTO.getSpeciality().length() > 50){
            errors.reject("speciality", "size");
        }

    }
}
