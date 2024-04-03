package com.example.springteacher5.service;

import com.example.springteacher5.dto.specialitiesDTO.SpecialityInsertDTO;
import com.example.springteacher5.dto.specialitiesDTO.SpecialityUpdateDTO;
import com.example.springteacher5.mapper.Mapper;
import com.example.springteacher5.model.Speciality;
import com.example.springteacher5.model.Teacher;
import com.example.springteacher5.repository.SpecialityRepository;
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
public class SpecialityServiceImpl implements ISpecialityService {

    private final SpecialityRepository specialityRepository;

    @Override
    @Transactional
    public Speciality insertSpeciality(SpecialityInsertDTO dto) throws Exception {
        Speciality speciality = null;
        try {
            speciality = specialityRepository.save(Mapper.mapToSpeciality(dto));
            if(speciality.getId() == null) throw new Exception("insert error");
            log.info("Speciality inserted succesfully");
            return speciality;
        }catch (Exception e){
            log.error("insert error "+ e.getMessage());
            throw e;
        }
    }

    @Override
    public Speciality updateSpeciality(SpecialityUpdateDTO dto) throws EntityNotFoundException {
        Speciality updatedSpeciality = null;
        Speciality speciality;
        try {
            speciality = specialityRepository.findSpecialityById(dto.getId());
            if(speciality.getId() == null) throw new EntityNotFoundException(Speciality.class, dto.getId());
            updatedSpeciality = specialityRepository.save(Mapper.mapToSpeciality(dto));
            log.info("Speciality updated succesfully");

        }catch (EntityNotFoundException e){
            log.error("Error in update specialityv +" + e.getMessage());
            throw e;
        }
        return speciality;
    }

    @Override
    @Transactional
    public Speciality deleteSpeciality(Long id) throws EntityNotFoundException {
        Speciality speciality = null;
        Speciality deletedSpeciality;
       try {
           speciality = specialityRepository.findSpecialityById(id);
           if (speciality.getId() == null) throw new EntityNotFoundException(Speciality.class,id);
           specialityRepository.deleteById(id);
           log.info("speciality deleted succesfully");
       }catch (EntityNotFoundException e){
           log.error("Error in updating speciality " + e.getMessage());
           throw e;
       }
       return speciality;

    }

    @Override
    public Speciality getSpecialityById(Long id) throws EntityNotFoundException {
        Speciality speciality;
        try {
            speciality = specialityRepository.findSpecialityById(id);
            if(speciality==null)throw new EntityNotFoundException(Speciality.class,id);
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return speciality;
    }

    @Override
    public List<Speciality> getBySpeciality(String speciality) throws EntityNotFoundException {
        List<Speciality> specialities = new ArrayList<>();
        try {
            specialities = specialityRepository.findBySpecialityStartingWith(speciality);
            if (specialities.isEmpty()) throw new EntityNotFoundException(Speciality.class,0L);
            log.info("Specialities with name starting with "+ speciality +" were found");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return specialities;
    }


}
