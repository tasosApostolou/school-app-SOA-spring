package com.example.springteacher5.dto.specialitiesDTO;

import com.example.springteacher5.dto.BaseDTO;

import com.example.springteacher5.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpecialityUpdateDTO extends BaseDTO {

    private String speciality;
    private Set<Teacher> teachers = new HashSet<>();

    public SpecialityUpdateDTO(Long id,String speciality, Set<Teacher> teachers) {
        this.setId(id);
        this.speciality = speciality;
        this.teachers = teachers;
    }
}
