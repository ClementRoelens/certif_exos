package com.example.routeservice.entity;

import com.example.routeservice.enums.RouteTypeEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private RouteTypeEnum type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int availableTickets;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "routes_stops",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "stop_id"))
    private List<Stop> stops;


    public Route() {}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RouteTypeEnum getType() {
        return type;
    }

    public void setType(RouteTypeEnum type) {
        this.type = type;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }
}
