package com.example.springteacher5.rest;


import com.example.springteacher5.dto.specialitiesDTO.SpecialityInsertDTO;
import com.example.springteacher5.dto.specialitiesDTO.SpecialityReadOnlyDTO;
import com.example.springteacher5.dto.specialitiesDTO.SpecialityUpdateDTO;
import com.example.springteacher5.mapper.Mapper;
import com.example.springteacher5.model.Speciality;
import com.example.springteacher5.model.Teacher;
import com.example.springteacher5.repository.TeacherRepository;
import com.example.springteacher5.service.ISpecialityService;
import com.example.springteacher5.service.ITeacherService;
import com.example.springteacher5.service.TeacherServiceImlpl;
import com.example.springteacher5.service.exceptions.EntityNotFoundException;

import com.example.springteacher5.validator.SpecialityInsertValidator;
import com.example.springteacher5.validator.SpecialityUpdateValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/speciality")
@RequiredArgsConstructor
public class SpecialityResController {
    private final ISpecialityService specialityService;
    private final SpecialityInsertValidator insertValidator;
//    private final SpecialityUpdateValidator updateValidator;
    @Autowired
private final TeacherRepository teacherRepository;


    @Operation(summary = "Get speciality by their name starting with initials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Speciality Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SpecialityReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid lastname supplied",
                    content = @Content)})
    @GetMapping("/specialities")
    public ResponseEntity<List<SpecialityReadOnlyDTO>> getSpecialityByName(@RequestParam("speciality") String specialityName) {
        List<Speciality> specialities;
        try {
            specialities = specialityService.getBySpeciality(specialityName);
            List<SpecialityReadOnlyDTO> readOnlyDTOS = new ArrayList<>();
            for (Speciality speciality : specialities) {
                readOnlyDTOS.add(Mapper.mapToReadOnlyDTO(speciality));
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            throw e;
        }
    }

    @Operation(summary = "Get a Speciality by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Speciality Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SpecialityReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Speciality not found",
                    content = @Content)})
    @GetMapping("/{specialityID}")
    public ResponseEntity<SpecialityReadOnlyDTO> getSpeciality(@PathVariable("id") Long id) {
        Speciality speciality;

        try {
            speciality = specialityService.getSpecialityById(id);
            SpecialityReadOnlyDTO dto = Mapper.mapToReadOnlyDTO(speciality);
            return ResponseEntity.ok(dto);

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Add a speciality")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Speciality created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SpecialityReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content)})
    @PostMapping("/specialities")
    public ResponseEntity<SpecialityReadOnlyDTO> addSpeciality(@Valid @RequestBody SpecialityInsertDTO dto, BindingResult bindingResult) {
        insertValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Speciality speciality = specialityService.insertSpeciality(dto);
            SpecialityReadOnlyDTO specialityReadOnlyDTO = Mapper.mapToReadOnlyDTO(speciality);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(specialityReadOnlyDTO.getId())
                    .toUri();
            return ResponseEntity.created(location).body(specialityReadOnlyDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Operation(summary = "Update a speciality")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "speciality updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SpecialityReadOnlyDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Speciality not found",
                    content = @Content) })
    @PutMapping("/specialities/{id}")
    public ResponseEntity<SpecialityReadOnlyDTO> updateSpeciality(@PathVariable("id") Long id, @Valid @RequestBody SpecialityUpdateDTO dto, BindingResult bindingResult) {
        if (!Objects.equals(id, dto.getId())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

//        insValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {

            Speciality speciality = specialityService.updateSpeciality(dto);
            SpecialityReadOnlyDTO readOnlyDTO = Mapper.mapToReadOnlyDTO(speciality);
            return new ResponseEntity<>(readOnlyDTO,HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "Delete a Speciality by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Speciality Deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SpecialityReadOnlyDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Speciality not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<SpecialityReadOnlyDTO> deleteSpeciality(@PathVariable("id") Long id){
        try {
            Speciality speciality = specialityService.deleteSpeciality(id);
            SpecialityReadOnlyDTO readOnlyDTO = Mapper.mapToReadOnlyDTO(speciality);
            return new ResponseEntity<>(readOnlyDTO,HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}




