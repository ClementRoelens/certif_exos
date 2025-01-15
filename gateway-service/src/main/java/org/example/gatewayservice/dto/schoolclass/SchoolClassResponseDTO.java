package org.example.gatewayservice.dto.schoolclass;

import org.example.gatewayservice.dto.student.StudentResponseDTO;
import org.example.gatewayservice.dto.teacher.TeacherResponseDTO;

import java.util.List;

public class SchoolClassResponseDTO {
    private TeacherResponseDTO teacher;
    private List<StudentResponseDTO> students;

    public SchoolClassResponseDTO(TeacherResponseDTO teacher, List<StudentResponseDTO> students) {
        this.teacher = teacher;
        this.students = students;
    }

    public TeacherResponseDTO getTeacher() {
        return teacher;
    }

    public List<StudentResponseDTO> getStudents() {
        return students;
    }

    public void setTeacher(TeacherResponseDTO teacher) {
        this.teacher = teacher;
    }

    public void setStudents(List<StudentResponseDTO> students) {
        this.students = students;
    }
}
