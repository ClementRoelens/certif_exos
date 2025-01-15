package org.example.studentservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "student")
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
//    private String schoolClassId;


    public Student() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

//    public String getSchoolClassId() {
//        return schoolClassId;
//    }
//
//    public void setSchoolClassId(String schoolClassId) {
//        this.schoolClassId = schoolClassId;
//    }
}
