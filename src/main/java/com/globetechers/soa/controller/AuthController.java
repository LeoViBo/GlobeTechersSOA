package com.globetechers.soa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globetechers.soa.dto.AuthResponseDTO;
import com.globetechers.soa.dto.LoginRequestDTO;
import com.globetechers.soa.dto.RegisterRequestDTO;
import com.globetechers.soa.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registerUser(@Valid @RequestBody RegisterRequestDTO dto) {
        AuthResponseDTO response = authService.registerUser(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO dto) {
        AuthResponseDTO response = authService.authenticateUser(dto);
        return ResponseEntity.ok(response);
    }
}