package org.example.exofiltreservlet.dto;

import org.example.exofiltreservlet.model.CelestObject;

import java.time.LocalDateTime;

public class CelestObjectDTO {
    private String name;
    private LocalDateTime dateTime;
    private String description;
    private double latitude;
    private double longitude;

    public CelestObjectDTO(String name, LocalDateTime dateTime, String description, double latitude, double longitude) {
        this.name = name;
        this.dateTime = dateTime;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public CelestObject toDTO(){
        return new CelestObject(name, dateTime, description, latitude, longitude);
    }
}
