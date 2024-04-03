package com.example.springteacher5.service;

import com.example.springteacher5.dto.teacherDTOs.TeacherUpdateDTO;
import com.example.springteacher5.dto.teacherDTOs.TeacherInsertDTO;
import com.example.springteacher5.mapper.Mapper;
import com.example.springteacher5.model.Teacher;
import com.example.springteacher5.repository.TeacherRepository;
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
public class TeacherServiceImlpl implements ITeacherService{
    private final TeacherRepository teacherRepository;

//    @Autowired
//    public TeacherServiceImlpl(TeacherRepository teacherRepository) {
//        this.teacherRepository = teacherRepository;
//    }

    @Transactional
    @Override
    public Teacher insert(TeacherInsertDTO dto) throws Exception {
        Teacher teacher = null;

        try{
            teacher = teacherRepository.save(Mapper.mapToTeacher(dto));
            if(teacher.getId()==null){
                throw new Exception("Insert error");
            }
            log.info("insert succes for teacher with id"+ teacher.getId());
            return teacher;
        }catch (Exception e){
            log.error("insert error "+ e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public Teacher update(TeacherUpdateDTO dto) throws EntityNotFoundException {
        Teacher teacher;
        Teacher updatedTeacher;
        try {
            teacher = teacherRepository.findTeacherById(dto.getId());
            if (teacher == null) throw new EntityNotFoundException(Teacher.class, dto.getId());
            updatedTeacher = teacherRepository.save(Mapper.mapToTeacher(dto));
            log.info("Teacher with id: "+ updatedTeacher.getId()+ " was updated");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return updatedTeacher;
    }

    @Transactional
    @Override
    public Teacher delete(Long id) throws EntityNotFoundException {
        Teacher teacher = null;

        try {
            teacher =  teacherRepository.findTeacherById(id);
            if (teacher == null) throw new EntityNotFoundException(Teacher.class,id);
            teacherRepository.deleteById(id);
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return teacher;
    }

    @Override
    public List<Teacher> getTeachersByLastname(String lastname) throws EntityNotFoundException {
List<Teacher> teachers = new ArrayList<>();
try {
    teachers = teacherRepository.findByLastnameStartingWith(lastname);
    if (teachers.isEmpty()) throw new EntityNotFoundException(Teacher.class,0L);
    log.info("Teachers with lastname starting with "+ lastname +" were found");
    }catch (EntityNotFoundException e){
    log.error(e.getMessage());
    throw e;
        }
return teachers;
    }

    @Override
   public Teacher getTeacherById(Long id) throws EntityNotFoundException {
Teacher teacher;
try {
    teacher = teacherRepository.findTeacherById(id);
    if(teacher==null)throw new EntityNotFoundException(Teacher.class,id);
}catch (EntityNotFoundException e){
    log.error(e.getMessage());
    throw e;
}
return teacher;
}

}
