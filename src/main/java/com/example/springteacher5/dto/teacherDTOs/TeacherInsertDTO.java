package com.example.springteacher5.dto.teacherDTOs;

import com.example.springteacher5.model.Speciality;
import com.example.springteacher5.model.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TeacherInsertDTO {
    @NotNull
    @Size(min = 5)
    private String ssn;
    //
    @NotNull
    @Size(min = 2, max = 255)
    private String firstname;

    @NotNull
    @Size(min = 2, max = 255)
    private String lastname;

    private User user;

    private Speciality speciality;
}
