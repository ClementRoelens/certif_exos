package org.example.teacherservice.repository;

import org.example.teacherservice.entity.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String> {
    List<Teacher> findTeachersBySubjectId(String subjectId);
}
