package org.example.teacherservice.controller;

import org.example.teacherservice.entity.Teacher;
import org.example.teacherservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();

        if (teachers != null){
            return new ResponseEntity<>(teachers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable String id) {
        Teacher teacher = teacherService.getTeacherById(id);

        if (teacher != null){
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/bySubject/{subjectId}")
    public ResponseEntity<List<Teacher>> getTeacherBySubject(@PathVariable String subjectId) {
        List<Teacher> teachers = teacherService.getTeacherBySubject(subjectId);

        if (teachers != null){
            return new ResponseEntity<>(teachers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        Teacher createdTeacher = teacherService.saveTeacher(teacher);

        if (createdTeacher != null){
            return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
