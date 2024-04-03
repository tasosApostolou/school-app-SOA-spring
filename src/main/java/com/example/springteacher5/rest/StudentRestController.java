package com.example.springteacher5.rest;


import com.example.springteacher5.dto.studentsDTO.StudentInsertDTO;
import com.example.springteacher5.dto.studentsDTO.StudentReadOnlyDTO;
import com.example.springteacher5.dto.studentsDTO.StudentUpdateDTO;
import com.example.springteacher5.mapper.Mapper;

import com.example.springteacher5.model.Student;
import com.example.springteacher5.service.IStudentService;
import com.example.springteacher5.service.exceptions.EntityNotFoundException;
import com.example.springteacher5.validator.StudentInsertValidator;
import com.example.springteacher5.validator.StudentUpdateValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentRestController {
    private final IStudentService studentService;
    private final StudentInsertValidator insertValidator;
    private final StudentUpdateValidator updateValidator;

    @Operation(summary = "Get students by their lastname starting with initials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid lastname supplied",
                    content = @Content)})
    @GetMapping("/students")
    public ResponseEntity<List<StudentReadOnlyDTO>> getStudentsByLastname(@RequestParam("lastname") String lastname) {
        List<Student> students;
        try {
            students = studentService.getStudentsByLastname(lastname);
            List<StudentReadOnlyDTO> readOnlyDTOS = new ArrayList<>();
            for (Student student : students) {
                readOnlyDTOS.add(Mapper.mapToReadOnlyDTO(student));
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            throw e;
        }
    }

    @Operation(summary = "Get a Student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content)})
    @GetMapping("/{studentID}")
    public ResponseEntity<StudentReadOnlyDTO> getStudent(@PathVariable("id") Long id) {
        Student student;

        try {
            student = studentService.getStudentById(id);
            StudentReadOnlyDTO dto = Mapper.mapToReadOnlyDTO(student);
            return ResponseEntity.ok(dto);

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Add a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Studentt created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content)})
    @PostMapping("/students")
    public ResponseEntity<StudentReadOnlyDTO> addStudent(@Valid @RequestBody StudentInsertDTO dto, BindingResult bindingResult) {
        insertValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Student student = studentService.insertStudent(dto);
            StudentReadOnlyDTO studentReadOnlyDTO = Mapper.mapToReadOnlyDTO(student);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(studentReadOnlyDTO.getId())
                    .toUri();
            return ResponseEntity.created(location).body(studentReadOnlyDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Operation(summary = "Update a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentReadOnlyDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content) })
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentReadOnlyDTO> updateStudent(@PathVariable("id") Long id, @Valid @RequestBody StudentUpdateDTO dto, BindingResult bindingResult) {
        if (!Objects.equals(id, dto.getId())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        updateValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {

            Student student = studentService.updateStudent(dto);
            StudentReadOnlyDTO readOnlyDTO = Mapper.mapToReadOnlyDTO(student);
            return new ResponseEntity<>(readOnlyDTO,HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "Delete a Student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student Deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentReadOnlyDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "student not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<StudentReadOnlyDTO> deleteStudent(@PathVariable("id") Long id){
        try {
            Student student = studentService.deleteStudent(id);
            StudentReadOnlyDTO readOnlyDTO = Mapper.mapToReadOnlyDTO(student);
            return new ResponseEntity<>(readOnlyDTO,HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

