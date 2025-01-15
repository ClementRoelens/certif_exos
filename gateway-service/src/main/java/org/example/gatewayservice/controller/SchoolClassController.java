package org.example.gatewayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.gatewayservice.dto.schoolclass.SchoolClassRequestDTO;
import org.example.gatewayservice.dto.schoolclass.SchoolClassResponseDTO;
import org.example.gatewayservice.dto.student.StudentRequestDTO;
import org.example.gatewayservice.dto.student.StudentResponseDTO;
import org.example.gatewayservice.dto.teacher.TeacherRequestDTO;
import org.example.gatewayservice.dto.teacher.TeacherResponseDTO;
import org.example.gatewayservice.service.RestClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.gatewayservice.util.ApiPortProvider.*;
import static org.example.gatewayservice.util.ApiSuffixProvider.*;

@RestController
@RequestMapping("api/schoolclass")
public class SchoolClassController {
    private final String API_ROOT = "http://localhost:";
    private final ObjectMapper objectMapper;


    public SchoolClassController() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }


    @GetMapping
    public List<SchoolClassResponseDTO> getSchoolClass() {
        RestClient<SchoolClassRequestDTO[]> client = new RestClient<>(API_ROOT + SCHOOLCLASS_PORT + SCHOOLCLASS_SUFFIX);
        SchoolClassRequestDTO[] schoolClassesRequests = client.getRequest(SchoolClassRequestDTO[].class);

        return Arrays.stream(schoolClassesRequests)
                .map(this::parseSchoolClass)
                .toList();
    }

    @GetMapping("/{id}")
    public SchoolClassResponseDTO getSchoolClass(@PathVariable("id") String id) {
        RestClient<SchoolClassRequestDTO> client = new RestClient<>(API_ROOT + SCHOOLCLASS_PORT + SCHOOLCLASS_SUFFIX + "/" + id);
        SchoolClassRequestDTO schoolclass = client.getRequest(SchoolClassRequestDTO.class);

        return parseSchoolClass(schoolclass);
    }

    @PostMapping
    public SchoolClassResponseDTO createSchoolClass(@RequestBody SchoolClassRequestDTO schoolclass) {
        RestClient<SchoolClassRequestDTO> client = new RestClient<>(API_ROOT + SCHOOLCLASS_PORT + SCHOOLCLASS_SUFFIX);

        try {
            SchoolClassRequestDTO createdSchoolclass = client.postRequest(objectMapper.writeValueAsString(schoolclass), SchoolClassRequestDTO.class);
            return parseSchoolClass(createdSchoolclass);
        } catch (JsonProcessingException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @PutMapping
    public SchoolClassResponseDTO updateSchoolClass(@RequestBody SchoolClassRequestDTO schoolclass) {
        RestClient<SchoolClassRequestDTO> client = new RestClient<>(API_ROOT + SCHOOLCLASS_PORT + SCHOOLCLASS_SUFFIX + "/" + schoolclass.getId());

        try {
            SchoolClassRequestDTO updatedSchoolclass = client.putRequest(objectMapper.writeValueAsString(schoolclass), SchoolClassRequestDTO.class);
            return parseSchoolClass(updatedSchoolclass);
        } catch (JsonProcessingException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private SchoolClassResponseDTO parseSchoolClass(SchoolClassRequestDTO DTO){
        RestClient<TeacherRequestDTO> teacherClient = new RestClient<>(API_ROOT +TEACHER_PORT + TEACHER_SUFFIX + "/" + DTO.getTeacherId());
        TeacherRequestDTO teacher = teacherClient.getRequest(TeacherRequestDTO.class);
        List<StudentResponseDTO> students = new ArrayList<>();
        RestClient<StudentRequestDTO> studentClient;

        for (String studentId : DTO.getStudentIds()){
            studentClient = new RestClient<>(API_ROOT + STUDENT_PORT + STUDENT_SUFFIX + "/" + studentId);
            StudentRequestDTO student = studentClient.getRequest(StudentRequestDTO.class);
            students.add(new StudentResponseDTO(student));
        }

        return new SchoolClassResponseDTO(TeacherController.parseTeacherDTO(teacher), students);
    }
}
