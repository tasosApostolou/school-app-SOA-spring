package com.example.springteacher5.model;

import com.example.springteacher5.Identity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
public class User extends AbstractEntity {
    @Column(length = 30)
    private String username;

    @Column(length = 50)
    private String password;

    @Column(name = "role")
    private String role;


    @OneToOne(mappedBy = "user")
    @Getter(AccessLevel.PROTECTED)
    private Student student;

//    @OneToMany(mappedBy = "user")
//    @Getter(AccessLevel.PROTECTED)
//    private Set<Teacher> teachers = new HashSet<>();


    public User(Long id,String username, String password, String role) {
        this.setId(id);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @OneToOne(mappedBy = "user")
    @Getter(AccessLevel.PROTECTED)
    private Teacher teacher;
    public void addTeacher(Teacher teacher){
        teacher.setUser(this);
        this.setRole("teacher");
    }

public void addStudent(Student student){
//    this.students.add(student);
//    student.setUser(this);
    student.setUser(this);
    this.setRole("student");
}


}