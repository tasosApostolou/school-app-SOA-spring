package com.example.springteacher5.validator;

import com.example.springteacher5.dto.studentsDTO.StudentInsertDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StudentInsertValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return StudentInsertDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudentInsertDTO studentInsertDTO = (StudentInsertDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstname","empty");
        if(studentInsertDTO.getFirstname().length()< 3 || studentInsertDTO.getFirstname().length() > 50){
            errors.reject("firstname", "size");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastname","empty");
        if(studentInsertDTO.getLastname().length()< 3 ||studentInsertDTO.getLastname().length() > 50){
            errors.reject("lastname", "size");
        }

//        if(teacherService.inUsernameExists(teachereInsertDTO.getUsername())){
//            errors.reject(("username","exists"));
//        }
    }


}

