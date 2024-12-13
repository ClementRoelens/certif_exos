package org.example.exofiltreservlet.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CelestObject {
    private int id;
    private String name;
    private LocalDateTime dateTime;
    private String description;
    private double latitude;
    private double longitude;
    private static int count = 1;

    public CelestObject(String name,  LocalDateTime dateTime, String description, double latitude, double longitude) {
        this.name = name;
        this.dateTime = dateTime;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        id = count++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public static int getCount() {
        return count;
    }
}
