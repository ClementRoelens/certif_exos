package org.example.gatewayservice.dto.teacher;

import java.time.LocalDate;

public class TeacherRequestDTO {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String subjectId;


    public TeacherRequestDTO() {
    }

    public TeacherRequestDTO(String id, String firstName, String lastName, LocalDate birthDate, String subjectId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.subjectId = subjectId;
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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
