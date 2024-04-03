package com.example.springteacher5.service;

import com.example.springteacher5.dto.studentsDTO.StudentInsertDTO;
import com.example.springteacher5.dto.studentsDTO.StudentUpdateDTO;
import com.example.springteacher5.mapper.Mapper;
import com.example.springteacher5.model.Student;
import com.example.springteacher5.model.Teacher;
import com.example.springteacher5.repository.StudentRepository;
import com.example.springteacher5.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {
    private  final StudentRepository studentRepository;
    @Transactional
    @Override
    public Student insertStudent(StudentInsertDTO studentDTO) throws Exception {
        Student student = null;
        try {
            student = studentRepository.save(Mapper.mapToStudent(studentDTO));
            if (student.getId() == null) throw new Exception("Error in insert");
            log.info("Insert succes for student with id: "+ student.getId());
            return student;

        }catch (EntityNotFoundException e){
            log.error("Error in insert "+ e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public Student updateStudent(StudentUpdateDTO dto) throws EntityNotFoundException {
        Student student;
        Student updatedStudent;
        try {
            student = studentRepository.findStudentById(dto.getId());
            if (student == null) throw new EntityNotFoundException(Teacher.class, dto.getId());
            updatedStudent = studentRepository.save(Mapper.mapToStudent(dto));
            log.info("Student with id: "+ updatedStudent.getId()+ " was updated");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return updatedStudent;
    }

    @Transactional
    @Override
    public Student deleteStudent(Long id) throws EntityNotFoundException {
        Student student = null;

        try {
            student =  studentRepository.findStudentById(id);
            if (student == null) throw new EntityNotFoundException(Teacher.class,id);
            studentRepository.deleteById(id);
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return student;
    }

    @Override
    public List<Student>getStudentsByLastname(String lastname) throws EntityNotFoundException {
        List<Student> students = new ArrayList<>();
        try {
            students = studentRepository.findByLastnameStartingWith(lastname);
            if (students.isEmpty()) throw new EntityNotFoundException(Teacher.class,0L);
            log.info("Students with lastname starting with "+ lastname +" were found");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return students;
    }

    @Override
    public Student getStudentById(Long id) throws EntityNotFoundException {
        Student student;
        try {
            student = studentRepository.findStudentById(id);
            if(student==null)throw new EntityNotFoundException(Student.class,id);
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return student;
    }

}
