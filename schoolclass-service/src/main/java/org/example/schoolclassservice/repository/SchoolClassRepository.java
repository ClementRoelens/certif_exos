package org.example.schoolclassservice.repository;

import org.example.schoolclassservice.entity.SchoolClass;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchoolClassRepository extends MongoRepository<SchoolClass, String> {
}
