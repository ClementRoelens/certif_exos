package org.example.schoolclassservice.controller;

import org.example.schoolclassservice.entity.SchoolClass;
import org.example.schoolclassservice.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/schoolclass")
public class SchoolClassController {
    @Autowired
    private SchoolClassService schoolClassService;

    @GetMapping
    public ResponseEntity<List<SchoolClass>> getAll() {
        List<SchoolClass> schoolClasses = schoolClassService.findAll();

        if (schoolClasses != null) {
            return new ResponseEntity<>(schoolClasses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("{id}")
    public ResponseEntity<SchoolClass> getById(@PathVariable String id) {
        SchoolClass schoolClass = schoolClassService.findById(id);

        if (schoolClass != null) {
            return new ResponseEntity<>(schoolClass, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<SchoolClass> create(@RequestBody SchoolClass schoolClass) {
        SchoolClass createdSchoolclass = schoolClassService.save(schoolClass);

        if (createdSchoolclass != null) {
            return new ResponseEntity<>(createdSchoolclass, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<SchoolClass> update(@RequestBody SchoolClass schoolClass) {
        SchoolClass toUpdateSchoolClass = schoolClassService.findById(schoolClass.getId());

        if (toUpdateSchoolClass != null) {
            SchoolClass updatedSchoolclass = schoolClassService.save(schoolClass);

            if (updatedSchoolclass != null) {
                return new ResponseEntity<>(updatedSchoolclass, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
