package com.example.springteacher5.dto.specialitiesDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpecialityInsertDTO{
    @NotNull
    private String speciality;


//    private Set<Teacher> teachers = new HashSet<>();


//    public void addTeacher(Teacher teacher){
//        this.teachers.add(teacher);
//        teacher.setSpeciality(this);
//    }
}
