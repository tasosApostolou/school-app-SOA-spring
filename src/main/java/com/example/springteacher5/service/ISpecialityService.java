package com.example.springteacher5.service;


import com.example.springteacher5.dto.specialitiesDTO.SpecialityInsertDTO;
import com.example.springteacher5.dto.specialitiesDTO.SpecialityUpdateDTO;
import com.example.springteacher5.model.Speciality;
import com.example.springteacher5.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface ISpecialityService {
    Speciality insertSpeciality(SpecialityInsertDTO specialityDTO) throws Exception;

    Speciality updateSpeciality(SpecialityUpdateDTO specialityDTO) throws EntityNotFoundException;
    Speciality deleteSpeciality(Long id) throws EntityNotFoundException;
    List<Speciality> getBySpeciality(String Speciality) throws EntityNotFoundException;
    Speciality getSpecialityById(Long id) throws EntityNotFoundException;


}
