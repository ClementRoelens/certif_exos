package com.example.accountservice.service;

import com.example.accountservice.config.jwt.JwtProvider;
import com.example.accountservice.dto.Credentials;
import com.example.accountservice.dto.UserInputDTO;
import com.example.accountservice.dto.UserOutputDTO;
import com.example.accountservice.entity.User;
import com.example.accountservice.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
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

    public String tryLogin(Credentials credentials){
        User user = userRepository.findByMail(credentials.getMail());

        if (user != null){
            if (passwordEncoder.matches(user.getPassword(), credentials.getPassword())){
                return generateToken(credentials.getMail(), credentials.getPassword(), user.getAuthorities());
            }
            return null;
        }
        return null;
    }

    private String generateToken(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password, authorities));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }

}
