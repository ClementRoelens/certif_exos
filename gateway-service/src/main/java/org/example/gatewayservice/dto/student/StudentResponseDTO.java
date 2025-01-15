package org.example.gatewayservice.dto.student;

import org.example.gatewayservice.dto.schoolclass.SchoolClassResponseDTO;

import java.time.LocalDate;

public class StudentResponseDTO {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private SchoolClassResponseDTO schoolClass;

    public StudentResponseDTO() {
    }

    public StudentResponseDTO(String firstName, String lastName, LocalDate birthDate, SchoolClassResponseDTO schoolClass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.schoolClass = schoolClass;
    }

    public StudentResponseDTO(StudentRequestDTO DTO){
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

    public void setSchoolClass(SchoolClassResponseDTO schoolClass) {
        this.schoolClass = schoolClass;
    }
}
