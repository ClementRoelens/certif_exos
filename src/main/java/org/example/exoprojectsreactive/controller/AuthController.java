package org.example.exoprojectsreactive.controller;

import org.example.exoprojectsreactive.dto.Credentials;
import org.example.exoprojectsreactive.jwt.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<Mono<String>> login(@RequestBody Credentials credentials) {
        if (credentials.isValid()) {
            String token = jwtService.generateToken("admin", Map.of("role", "ROLE_ADMIN"));
            return new ResponseEntity<>(Mono.just(token), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(Mono.just("Credentials incorrects"),HttpStatus.BAD_REQUEST);
    }
}
