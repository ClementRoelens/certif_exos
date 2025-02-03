package com.example.routeservice.service;

import com.example.routeservice.repository.RouteRepository;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
    private final RouteRepository routeRepository;


    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }



}
