package com.example.accountservice.service;

import com.example.accountservice.dto.UserInputDTO;
import com.example.accountservice.dto.UserOutputDTO;
import com.example.accountservice.entity.User;
import com.example.accountservice.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public UserOutputDTO createUser(UserInputDTO dto){
        User user = new User(dto);
        user.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );
        user = userRepository.save(user);
        return new UserOutputDTO(user);
    }

    public UserOutputDTO ediUser(String id, UserInputDTO dto){
        User user = userRepository.findById(id).orElse(null);

        if (user != null){
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setMail(dto.getMail());
            user.setPhoneNumber(dto.getPhoneNumber());
            user.setPassword(
                    passwordEncoder.encode(dto.getPassword())
            );
            user = userRepository.save(user);
            return new UserOutputDTO(user);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        return userRepository.findByMail(mail);
    }
}
