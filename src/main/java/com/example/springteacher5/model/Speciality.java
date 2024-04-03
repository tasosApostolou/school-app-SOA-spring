package com.example.springteacher5.model;

import com.example.springteacher5.Identity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
//@AllArgsConstructor
@Setter
@Getter
//@ToString
public class Speciality extends AbstractEntity {
    @Column(length = 100)
    private String speciality;

    @OneToMany(mappedBy = "speciality")
    @Getter(AccessLevel.PUBLIC)
    private Set<Teacher> teachers = new HashSet<>();


    public Speciality(Long id,String speciality, Set<Teacher> teachers) {
        this.setId(id);
        this.speciality = speciality;
        this.teachers = teachers;
    }

    public Set<Teacher> getAllTeacherss() {
        return Collections.unmodifiableSet(teachers);
    }


}
