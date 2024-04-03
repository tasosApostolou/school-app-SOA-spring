package com.example.springteacher5.mapper;

import com.example.springteacher5.dto.specialitiesDTO.SpecialityInsertDTO;
import com.example.springteacher5.dto.specialitiesDTO.SpecialityReadOnlyDTO;
import com.example.springteacher5.dto.specialitiesDTO.SpecialityUpdateDTO;
import com.example.springteacher5.dto.studentsDTO.StudentInsertDTO;
import com.example.springteacher5.dto.studentsDTO.StudentReadOnlyDTO;
import com.example.springteacher5.dto.studentsDTO.StudentUpdateDTO;
import com.example.springteacher5.dto.teacherDTOs.TeacherReadOnlyDTO;
import com.example.springteacher5.dto.teacherDTOs.TeacherUpdateDTO;
import com.example.springteacher5.dto.teacherDTOs.TeacherInsertDTO;
import com.example.springteacher5.dto.usersDTO.UserInsertDTO;
import com.example.springteacher5.dto.usersDTO.UserReadOnlyDTO;
import com.example.springteacher5.dto.usersDTO.UserUpdateDTO;
import com.example.springteacher5.model.Speciality;
import com.example.springteacher5.model.Student;
import com.example.springteacher5.model.Teacher;
import com.example.springteacher5.model.User;

public class Mapper {

    public static Teacher mapToTeacher(TeacherInsertDTO dto) {
        return new Teacher(null, dto.getSsn(), dto.getFirstname(), dto.getLastname(), dto.getUser(), dto.getSpeciality());
    }

    public static Teacher mapToTeacher(TeacherUpdateDTO dto) {
        return new Teacher(dto.getId(), dto.getSsn(), dto.getFirstname(), dto.getLastname(), dto.getUser(), dto.getSpeciality());
    }

    public static TeacherReadOnlyDTO mapToReadOnlyDTO(Teacher teacher) {
        return new TeacherReadOnlyDTO(teacher.getId(), teacher.getSsn(), teacher.getFirstname(), teacher.getLastname(), teacher.getUser(), teacher.getSpeciality());
    }


    public static Speciality mapToSpeciality(SpecialityInsertDTO dto) {
        return new Speciality(null, dto.getSpeciality(), null);
    }

    public static Speciality mapToSpeciality(SpecialityUpdateDTO dto) {
        return new Speciality(dto.getId(), dto.getSpeciality(), dto.getTeachers());
    }

    public static SpecialityReadOnlyDTO mapToReadOnlyDTO(Speciality speciality) {
        return new SpecialityReadOnlyDTO(speciality.getId(), speciality.getSpeciality(), speciality.getTeachers());
    }


    public static Student mapToTeacher(StudentInsertDTO dto) {
        return new Student(null, dto.getFirstname(), dto.getLastname(), dto.getGender(), dto.getBirthdate(), dto.getCity(), dto.getUser());
    }


    public static Student mapToStudent(StudentUpdateDTO dto) {
        return new Student(dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getGender(), dto.getBirthdate(), dto.getCity(), dto.getUser());
    }
    public static Student mapToStudent(StudentInsertDTO dto) {
        return new Student(null, dto.getFirstname(), dto.getLastname(), dto.getGender(), dto.getBirthdate(), dto.getCity(), dto.getUser());
    }

    public static StudentReadOnlyDTO mapToReadOnlyDTO(Student student) {
        return new StudentReadOnlyDTO(student.getId(), student.getFirstname(), student.getLastname(), student.getGender(), student.getBirthdate(), student.getCity(), student.getUser());
    }


    public static User mapToUser(UserInsertDTO dto) {
        return new User(null, dto.getUsername(), dto.getPassword(), dto.getRole());

    }

    public static User mapToUser(UserUpdateDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getRole());
    }

    public static UserReadOnlyDTO mapToReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }


}