package com.example.springteacher5.service;




import com.example.springteacher5.dto.studentsDTO.StudentInsertDTO;
import com.example.springteacher5.dto.studentsDTO.StudentUpdateDTO;
import com.example.springteacher5.model.Student;
import com.example.springteacher5.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IStudentService {
    Student insertStudent(StudentInsertDTO teacherDTO) throws Exception;
    Student updateStudent(StudentUpdateDTO teacherDTO) throws EntityNotFoundException;
    Student deleteStudent(Long id) throws EntityNotFoundException;
    List<Student> getStudentsByLastname(String lastname) throws EntityNotFoundException;
    Student getStudentById(Long id) throws EntityNotFoundException;

}
