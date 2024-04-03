package com.example.springteacher5.dto.teacherDTOs;

import com.example.springteacher5.Identity.AbstractEntity;
import com.example.springteacher5.dto.BaseDTO;
import com.example.springteacher5.model.Speciality;
import com.example.springteacher5.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeacherUpdateDTO extends BaseDTO {
    String ssn;
    String firstname;
    String lastname;
    User user;
    Speciality speciality;

    public TeacherUpdateDTO(Long id,String ssn, String firstname, String lastname,User user, Speciality speciality) {
        super.setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.user = user;
        this.speciality=speciality;
    }
}
