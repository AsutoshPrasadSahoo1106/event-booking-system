package com.asutosh.event_booking_system.controller;

import com.asutosh.event_booking_system.dto.LoginRequest;
import com.asutosh.event_booking_system.dto.RegisterRequest;
import com.asutosh.event_booking_system.security.JwtUtil;
import com.asutosh.event_booking_system.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.function.ServerResponse.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;


    @PostMapping("/register")
    ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request){
        authService.register(request);
        return ResponseEntity.ok("User is registered successfully");
    }

    @PostMapping("/login")
    ResponseEntity<String> login(@Valid @RequestBody LoginRequest request){
        String token = authService.login(request);
        return ResponseEntity.ok(token);
    }


}
