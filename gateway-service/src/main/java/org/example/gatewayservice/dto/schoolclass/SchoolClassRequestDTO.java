package org.example.gatewayservice.dto.schoolclass;

import java.util.List;

public class SchoolClassRequestDTO {
    private String id;
    private String teacherId;
    private List<String> studentIds;

    public SchoolClassRequestDTO() {
    }

    public SchoolClassRequestDTO(String id, String teacherId, List<String> studentsId) {
        this.id = id;
        this.teacherId = teacherId;
        this.studentIds = studentsId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public List<String> getStudentIds() {
        return studentIds;
    }
}
