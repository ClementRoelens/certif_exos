package org.example.subjectservice.service;

import org.example.subjectservice.entity.Subject;
import org.example.subjectservice.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(String id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }
}
