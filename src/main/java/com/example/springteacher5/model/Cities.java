package com.example.springteacher5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import com.example.springteacher5.Identity.AbstractEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cities extends AbstractEntity {
    @Column(length = 50)
    private String city;

    @OneToMany(mappedBy = "city")
    @Getter(AccessLevel.PROTECTED)
    private Set<Student> students = new HashSet<>();

    public void addStudent(Student student){
        this.students.add(student);
        student.setCity(this);
    }
}
