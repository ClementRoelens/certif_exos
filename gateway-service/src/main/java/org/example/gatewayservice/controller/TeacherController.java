package org.example.gatewayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.gatewayservice.dto.subject.SubjectRequestDTO;
import org.example.gatewayservice.dto.subject.SubjectResponseDTO;
import org.example.gatewayservice.dto.teacher.TeacherRequestDTO;
import org.example.gatewayservice.dto.teacher.TeacherResponseDTO;
import org.example.gatewayservice.service.RestClient;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.example.gatewayservice.util.ApiPortProvider.SUBJECT_PORT;
import static org.example.gatewayservice.util.ApiPortProvider.TEACHER_PORT;
import static org.example.gatewayservice.util.ApiSuffixProvider.SUBJECT_SUFFIX;
import static org.example.gatewayservice.util.ApiSuffixProvider.TEACHER_SUFFIX;

@RestController
@RequestMapping("api/teacher")
public class TeacherController {
    private static final String API_ROOT = "http://localhost:";
    private final ObjectMapper objectMapper;


    public TeacherController() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }


    @GetMapping
    public List<TeacherResponseDTO> getTeachers() {
        RestClient<TeacherRequestDTO[]> client = new RestClient<>(API_ROOT + TEACHER_PORT + TEACHER_SUFFIX);
        TeacherRequestDTO[] teachers = client.getRequest(TeacherRequestDTO[].class);

        return Arrays.stream(teachers)
                .map(TeacherController::parseTeacherDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public TeacherResponseDTO getTeacher(@PathVariable("id") String id) {
        RestClient<TeacherRequestDTO> client = new RestClient<>(API_ROOT + TEACHER_PORT + TEACHER_SUFFIX + "/" + id);
        TeacherRequestDTO teacher = client.getRequest(TeacherRequestDTO.class);

        return parseTeacherDTO(teacher);
    }

    @PostMapping
    public TeacherResponseDTO createTeacher(@RequestBody TeacherRequestDTO teacher) {
        RestClient<TeacherRequestDTO> client = new RestClient<>(API_ROOT + TEACHER_PORT + TEACHER_SUFFIX);
        TeacherRequestDTO createdTeacher = null;
        try {
            createdTeacher = client.postRequest(objectMapper.writeValueAsString(teacher), TeacherRequestDTO.class);
            return parseTeacherDTO(createdTeacher);
        } catch (JsonProcessingException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }


    public static TeacherResponseDTO parseTeacherDTO(TeacherRequestDTO DTO){
        RestClient<SubjectRequestDTO> restClient = new RestClient<>(API_ROOT + SUBJECT_PORT + SUBJECT_SUFFIX + "/" + DTO.getSubjectId());
        SubjectRequestDTO subjectRequest = restClient.getRequest(SubjectRequestDTO.class);
        SubjectResponseDTO subject = new SubjectResponseDTO(subjectRequest);
        TeacherResponseDTO teacher = new TeacherResponseDTO(DTO);
        teacher.setSubject(subject);
        return teacher;
    }
}
