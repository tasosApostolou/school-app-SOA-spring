package com.example.springteacher5.repository;

import com.example.springteacher5.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByLastnameStartingWith(String lastname);
    Student findStudentById(Long id);
}
