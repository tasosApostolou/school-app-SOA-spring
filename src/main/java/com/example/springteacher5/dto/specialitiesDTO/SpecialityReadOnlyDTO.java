package com.example.springteacher5.dto.specialitiesDTO;

import com.example.springteacher5.dto.BaseDTO;
import com.example.springteacher5.model.Teacher;

import java.util.HashSet;
import java.util.Set;

public class SpecialityReadOnlyDTO extends BaseDTO {
    private String speciality;
    private Set<Teacher> teachers = new HashSet<>();

    public SpecialityReadOnlyDTO(Long id, String speciality, Set<Teacher> teachers) {
        super.setId(id);
        this.speciality = speciality;
        this.teachers = teachers;
    }
}
