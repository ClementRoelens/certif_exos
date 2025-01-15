package org.example.gradeservice.controller;

import org.example.gradeservice.entity.Grade;
import org.example.gradeservice.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping
    public ResponseEntity<List<Grade>> getGrades() {
        List<Grade> grades = gradeService.getAllGrades();

        if (grades != null) {
            return new ResponseEntity<>(grades, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGrade(@PathVariable String id) {
        Grade grade = gradeService.getGradeById(id);

        if (grade != null) {
            return new ResponseEntity<>(grade, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("byStudent/{studentId}")
    public ResponseEntity<List<Grade>> getGradesByStudentId(@PathVariable String studentId) {
        List<Grade> grades = gradeService.getGradesByStudent(studentId);

        if (grades != null) {
            return new ResponseEntity<>(grades, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<Grade> addGrade(@RequestBody Grade grade) {
        Grade createdGrade = gradeService.addGrade(grade);

        if (createdGrade != null) {
            return new ResponseEntity<>(createdGrade, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
