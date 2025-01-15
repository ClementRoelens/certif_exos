package org.example.gradeservice.service;

import org.example.gradeservice.entity.Grade;
import org.example.gradeservice.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public Grade getGradeById(String id) {
        return gradeRepository.findById(id).orElse(null);
    }

    public List<Grade> getGradesByStudent(String studentId) {
        return gradeRepository.findGradeByStudentId(studentId);
    }

    public Grade addGrade(Grade grade) {
        return gradeRepository.save(grade);
    }
}
