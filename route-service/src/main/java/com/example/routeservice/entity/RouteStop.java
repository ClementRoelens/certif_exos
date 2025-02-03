package com.example.routeservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "routes_stops")
public class RouteStop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
    @ManyToOne
    @JoinColumn(name = "stop_id")
    private Stop stop;
    private LocalTime time;

}
