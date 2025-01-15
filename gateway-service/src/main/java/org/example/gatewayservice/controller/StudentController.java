package org.example.gatewayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.gatewayservice.dto.student.StudentRequestDTO;
import org.example.gatewayservice.dto.student.StudentResponseDTO;
import org.example.gatewayservice.service.RestClient;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.example.gatewayservice.util.ApiPortProvider.STUDENT_PORT;
import static org.example.gatewayservice.util.ApiSuffixProvider.STUDENT_SUFFIX;


@RestController
@RequestMapping("api/student")
public class StudentController {
    private final String API_ROOT = "http://localhost:";
    private final ObjectMapper objectMapper;


    public StudentController() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }


    @GetMapping
    public List<StudentResponseDTO> getStudents() {
        RestClient<StudentRequestDTO[]> client = new RestClient<>(API_ROOT + STUDENT_PORT + STUDENT_SUFFIX);
        StudentRequestDTO[] students =client.getRequest(StudentRequestDTO[].class);

        return Arrays.stream(students)
                .map(StudentResponseDTO::new)
                .toList();
    }

    @GetMapping("{id}")
    public StudentResponseDTO getStudentById(@PathVariable("id") String id) {
        RestClient<StudentRequestDTO> client = new RestClient<>(API_ROOT + STUDENT_PORT + STUDENT_SUFFIX + "/" + id);
        StudentRequestDTO request = client.getRequest(StudentRequestDTO.class);

        return new StudentResponseDTO(request);
    }

    @PostMapping
    public StudentResponseDTO createStudent(@RequestBody StudentRequestDTO student) {
        RestClient<StudentRequestDTO> client = new RestClient<>(API_ROOT + STUDENT_PORT + STUDENT_SUFFIX);
        try {
            StudentRequestDTO studentResponse = client.postRequest(objectMapper.writeValueAsString(student),StudentRequestDTO.class);
            return new StudentResponseDTO(studentResponse);
        } catch (JsonProcessingException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
