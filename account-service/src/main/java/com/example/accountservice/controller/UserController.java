package com.example.accountservice.controller;

import com.example.accountservice.dto.Credentials;
import com.example.accountservice.dto.UserInputDTO;
import com.example.accountservice.dto.UserOutputDTO;
import com.example.accountservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserInputDTO user, BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            UserOutputDTO createdUser = userService.createUser(user);

            if (createdUser != null){
                return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage())
                );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Credentials credentials){
        String token = userService.tryLogin(credentials);

        if (token != null){
            return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@Valid @RequestBody UserInputDTO user, @PathVariable("id") String id, BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            UserOutputDTO updatedUser = userService.ediUser(id,user);

            if (updatedUser != null){
                return new ResponseEntity<>(updatedUser,HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage())
                );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
