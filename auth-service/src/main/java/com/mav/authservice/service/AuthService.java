package com.mav.authservice.service;

import com.mav.authservice.dto.LoginRequestDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    
    public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    
    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
        
        return userService
                .findByEmail(loginRequestDTO.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDTO.getPassword(),
                        u.getPassword()))
                .map(u -> jwtUtil.generateToket(u.getEmail(), u.getRole()));
    }
}
