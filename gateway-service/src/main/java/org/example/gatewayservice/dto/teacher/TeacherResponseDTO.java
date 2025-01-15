package org.example.gatewayservice.dto.teacher;

import org.example.gatewayservice.dto.schoolclass.SchoolClassResponseDTO;
import org.example.gatewayservice.dto.subject.SubjectResponseDTO;

import java.time.LocalDate;

public class TeacherResponseDTO {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private SubjectResponseDTO subject;
    private SchoolClassResponseDTO schoolClass;

    public TeacherResponseDTO(){

    }

    public TeacherResponseDTO(String firstName, String lastName, LocalDate birthDate, SubjectResponseDTO subject, SchoolClassResponseDTO schoolClass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.subject = subject;
        this.schoolClass = schoolClass;
    }

    public TeacherResponseDTO(TeacherRequestDTO DTO){
        this.firstName = DTO.getFirstName();
        this.lastName = DTO.getLastName();
        this.birthDate = DTO.getBirthDate();
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public SubjectResponseDTO getSubject() {
        return subject;
    }

    public SchoolClassResponseDTO getSchoolClass() {
        return schoolClass;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setSubject(SubjectResponseDTO subject) {
        this.subject = subject;
    }

    public void setSchoolClass(SchoolClassResponseDTO schoolClass) {
        this.schoolClass = schoolClass;
    }
}
