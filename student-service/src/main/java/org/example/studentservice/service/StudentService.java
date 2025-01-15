package org.example.studentservice.service;

import org.example.studentservice.entity.Student;
import org.example.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
}
