package org.example.schoolclassservice.service;

import org.example.schoolclassservice.entity.SchoolClass;
import org.example.schoolclassservice.repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolClassService {
    @Autowired
    private SchoolClassRepository schoolClassRepository;

    public List<SchoolClass> findAll() {
        return schoolClassRepository.findAll();
    }

    public SchoolClass findById(String id) {
        return schoolClassRepository.findById(id).orElse(null);
    }

    public SchoolClass save(SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }

    public SchoolClass update(SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }
}
