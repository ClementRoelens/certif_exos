package org.example.gatewayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.gatewayservice.dto.grade.GradeRequestDTO;
import org.example.gatewayservice.dto.grade.GradeResponseDTO;
import org.example.gatewayservice.dto.student.StudentRequestDTO;
import org.example.gatewayservice.dto.student.StudentResponseDTO;
import org.example.gatewayservice.dto.subject.SubjectRequestDTO;
import org.example.gatewayservice.dto.subject.SubjectResponseDTO;
import org.example.gatewayservice.service.RestClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.example.gatewayservice.util.ApiPortProvider.*;
import static org.example.gatewayservice.util.ApiSuffixProvider.*;

@RestController
@RequestMapping("api/grade")
public class GradeController {
    private final String API_ROOT = "http://localhost:";
    private final ObjectMapper objectMapper;


    public GradeController() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }


    @GetMapping
    public ResponseEntity<List<GradeResponseDTO>> getAllGrades() {
        RestClient<GradeRequestDTO[]> restClient = new RestClient<>(API_ROOT + GRADE_PORT + GRADE_SUFFIX);
        GradeRequestDTO[] grades = restClient.getRequest(GradeRequestDTO[].class);

        if (grades != null) {
            return new ResponseEntity<>(
                    Arrays.stream(grades)
                            .map(this::parseDTO)
                            .toList(),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeResponseDTO> getGradeById(@PathVariable("id") String id) {
        RestClient<GradeRequestDTO> restClient = new RestClient<>(API_ROOT + GRADE_PORT + GRADE_SUFFIX + "/" + id);
        GradeRequestDTO grade = restClient.getRequest(GradeRequestDTO.class);

        if (grade != null) {
            return new ResponseEntity<>(parseDTO(grade), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity createGrade(@RequestBody GradeRequestDTO grade) {
        RestClient<GradeRequestDTO> restClient = new RestClient<>(API_ROOT + GRADE_PORT + GRADE_SUFFIX);
        try {
            GradeRequestDTO createdGrade = restClient.postRequest(objectMapper.writeValueAsString(grade), GradeRequestDTO.class);
            if (createdGrade != null) {
                return new ResponseEntity<>(parseDTO(createdGrade), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (JsonProcessingException e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private GradeResponseDTO parseDTO(GradeRequestDTO dto) {
        GradeResponseDTO grade = new GradeResponseDTO();
        grade.setValue(dto.getValue());

        RestClient<SubjectRequestDTO> subjectClient = new RestClient<>(API_ROOT + SUBJECT_PORT + SUBJECT_SUFFIX + "/" + dto.getSubjectId());
        RestClient<StudentRequestDTO> studentClient = new RestClient<>(API_ROOT + STUDENT_PORT + STUDENT_SUFFIX + "/" + dto.getStudentId());

        StudentRequestDTO studentRequest = studentClient.getRequest(StudentRequestDTO.class);
        SubjectRequestDTO subjectRequest = subjectClient.getRequest(SubjectRequestDTO.class);

        grade.setStudent(new StudentResponseDTO(studentRequest));
        grade.setSubject(new SubjectResponseDTO(subjectRequest));

        return grade;
    }
}
