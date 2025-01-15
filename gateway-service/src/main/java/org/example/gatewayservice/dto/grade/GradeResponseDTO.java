package org.example.gatewayservice.dto.grade;

import org.example.gatewayservice.dto.student.StudentResponseDTO;
import org.example.gatewayservice.dto.subject.SubjectResponseDTO;

public class GradeResponseDTO {
    private int value;
    private StudentResponseDTO student;
    private SubjectResponseDTO subject;


    public GradeResponseDTO() {
    }

    public GradeResponseDTO(int value, StudentResponseDTO student, SubjectResponseDTO subject) {
        this.value = value;
        this.student = student;
        this.subject = subject;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public StudentResponseDTO getStudent() {
        return student;
    }

    public void setStudent(StudentResponseDTO student) {
        this.student = student;
    }

    public SubjectResponseDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectResponseDTO subject) {
        this.subject = subject;
    }
}
