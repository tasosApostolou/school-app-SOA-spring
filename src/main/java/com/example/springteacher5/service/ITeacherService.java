package com.example.springteacher5.service;

import com.example.springteacher5.dto.teacherDTOs.TeacherUpdateDTO;
import com.example.springteacher5.dto.teacherDTOs.TeacherInsertDTO;
import com.example.springteacher5.model.Teacher;
import com.example.springteacher5.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface ITeacherService {
    Teacher insert(TeacherInsertDTO dto) throws Exception;

    Teacher update(TeacherUpdateDTO dto) throws EntityNotFoundException;

    Teacher delete(Long id) throws EntityNotFoundException;
    List<Teacher> getTeachersByLastname(String lastname) throws EntityNotFoundException;
    Teacher getTeacherById(Long id) throws EntityNotFoundException;


}
