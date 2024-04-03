package com.example.springteacher5.dto.studentsDTO;


import com.example.springteacher5.dto.BaseDTO;
import com.example.springteacher5.model.Cities;
import com.example.springteacher5.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentReadOnlyDTO extends BaseDTO {


   private String firstname;
   private String lastname;
   private String gender;
   private String birthdate;
   private Cities city;
   private User user;

    public StudentReadOnlyDTO(Long id,String firstname, String lastname, String gender, String birthdate, Cities city, User user) {
        this.setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.city = city;
        this.user = user;
    }
}
