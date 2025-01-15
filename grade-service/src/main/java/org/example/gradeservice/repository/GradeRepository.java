package org.example.gradeservice.repository;

import org.example.gradeservice.entity.Grade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends MongoRepository<Grade, String> {
    List<Grade> findGradeByStudentId(String studentId);
}
