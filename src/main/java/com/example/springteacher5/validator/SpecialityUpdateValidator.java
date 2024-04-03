package com.example.springteacher5.validator;

import com.example.springteacher5.dto.specialitiesDTO.SpecialityUpdateDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class SpecialityUpdateValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SpecialityUpdateDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SpecialityUpdateDTO specialityUpdateDTO = (SpecialityUpdateDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"speciality","empty");
        if(specialityUpdateDTO.getSpeciality().length()< 3 || specialityUpdateDTO.getSpeciality().length() > 50){
            errors.reject("speciality", "size");
        }


    }
}


