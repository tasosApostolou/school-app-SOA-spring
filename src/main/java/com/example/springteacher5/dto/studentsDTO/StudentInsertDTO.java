package com.example.springteacher5.dto.studentsDTO;


import com.example.springteacher5.model.Cities;
import com.example.springteacher5.model.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentInsertDTO {

    @NotNull
    @Size(min = 2, max = 255)
    private String firstname;

    @NotNull
    @Size(min = 2, max = 255)
    private String lastname;

    @NotNull
    private String gender;

//    @NotNull
    private String birthdate;

    private Cities city;


    private User user;


}
