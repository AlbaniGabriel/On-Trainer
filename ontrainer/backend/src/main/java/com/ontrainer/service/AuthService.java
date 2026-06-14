package com.ontrainer.service;

import com.ontrainer.config.JwtUtil;
import com.ontrainer.dto.Dtos;
import com.ontrainer.entity.User;
import com.ontrainer.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public Dtos.AuthResponse register(Dtos.RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail()))
            throw new RuntimeException("Email ja cadastrado: " + req.getEmail());
        User user = User.builder()
                .username(req.getUsername()).email(req.getEmail())
                .passwordHash(passwordEncoder.encode(req.getPassword()))
                .phone(req.getPhone()).build();
        user = userRepository.save(user);
        String token = jwtUtil.generateToken(user.getId(), user.getEmail());
        return Dtos.AuthResponse.builder().token(token).userId(user.getId())
                .username(user.getRealUsername()).email(user.getEmail()).build();
    }

    public Dtos.AuthResponse login(Dtos.LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ou senha invalidos"));
        if (!passwordEncoder.matches(req.getPassword(), user.getPasswordHash()))
            throw new RuntimeException("Email ou senha invalidos");
        String token = jwtUtil.generateToken(user.getId(), user.getEmail());
        return Dtos.AuthResponse.builder().token(token).userId(user.getId())
                .username(user.getRealUsername()).email(user.getEmail()).build();
    }
}
