package com.crio.rentvideo_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crio.rentvideo_service.controller.exchange.request.AuthRequest;
import com.crio.rentvideo_service.controller.exchange.request.RegisterRequest;
import com.crio.rentvideo_service.controller.exchange.response.AuthResponse;
import com.crio.rentvideo_service.service.AuthService;

import jakarta.validation.Valid;

@RequestMapping("/api")
@RestController
public class AuthController {
  
  @Autowired
  AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authService.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }

}
