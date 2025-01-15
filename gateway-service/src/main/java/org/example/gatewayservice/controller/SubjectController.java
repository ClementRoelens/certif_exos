package org.example.gatewayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.gatewayservice.dto.subject.SubjectRequestDTO;
import org.example.gatewayservice.dto.subject.SubjectResponseDTO;
import org.example.gatewayservice.service.RestClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.example.gatewayservice.util.ApiPortProvider.SUBJECT_PORT;
import static org.example.gatewayservice.util.ApiSuffixProvider.SUBJECT_SUFFIX;

@RestController
@RequestMapping("api/subject")
public class SubjectController {
    private final String API_ROOT = "http://localhost:";
    private final ObjectMapper objectMapper;


    public SubjectController() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponseDTO>> getAllSubjects() {
        RestClient<SubjectRequestDTO[]> restClient = new RestClient<>(API_ROOT + SUBJECT_PORT + SUBJECT_SUFFIX);
        SubjectRequestDTO[] subjects = restClient.getRequest(SubjectRequestDTO[].class);

        if (subjects != null) {
            return new ResponseEntity<>(
                    Arrays.stream(subjects)
                            .map(SubjectResponseDTO::new)
                            .toList(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDTO> getSubjectById(@PathVariable("id") String id) {
        RestClient<SubjectRequestDTO> restClient = new RestClient<>(API_ROOT + SUBJECT_PORT + SUBJECT_SUFFIX + "/" + id);
        SubjectRequestDTO request = restClient.getRequest(SubjectRequestDTO.class);

        if (request != null) {
            return new ResponseEntity<>(new SubjectResponseDTO(request), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity createSubject(@RequestBody SubjectRequestDTO request) {
        RestClient<SubjectRequestDTO> restClient = new RestClient<>(API_ROOT + SUBJECT_PORT + SUBJECT_SUFFIX);
        try {
            SubjectRequestDTO createdRequest = restClient.postRequest(objectMapper.writeValueAsString(request), SubjectRequestDTO.class);
            if (createdRequest != null) {
                return new ResponseEntity<>(new SubjectResponseDTO(createdRequest), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (JsonProcessingException e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
