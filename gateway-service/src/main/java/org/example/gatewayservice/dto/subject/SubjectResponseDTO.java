package org.example.gatewayservice.dto.subject;

public class SubjectResponseDTO {
    private String name;
    private int coeff;


    public SubjectResponseDTO() {
    }

    public SubjectResponseDTO(String name, int coeff) {
        this.name = name;
        this.coeff = coeff;
    }

    public SubjectResponseDTO(SubjectRequestDTO DTO){
        this.name = DTO.getName();
        this.coeff = DTO.getCoeff();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoeff() {
        return coeff;
    }

    public void setCoeff(int coeff) {
        this.coeff = coeff;
    }
}
