package org.example.gatewayservice.dto.student;

import java.time.LocalDate;

public class StudentRequestDTO {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;


    public StudentRequestDTO() {
    }

    public StudentRequestDTO(String id, String firstName, String lastName, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
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
