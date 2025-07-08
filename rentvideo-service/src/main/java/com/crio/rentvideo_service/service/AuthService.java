package com.crio.rentvideo_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crio.rentvideo_service.controller.exchange.request.AuthRequest;
import com.crio.rentvideo_service.controller.exchange.request.RegisterRequest;
import com.crio.rentvideo_service.controller.exchange.response.AuthResponse;
import com.crio.rentvideo_service.model.User;
import com.crio.rentvideo_service.model.enums.Role;
import com.crio.rentvideo_service.repository.UserRepository;


@Service
public class AuthService {
  
  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  UserRepository userRepository;

  @Autowired
  AuthenticationManager authenticationManager;

  public AuthResponse register(RegisterRequest request) {
    
    if (request.getRole() == null ) {
      request.setRole(Role.CUSTOMER);
    }

    User user = User.builder()
      .firstName(request.getFirstName()) 
      .email(request.getEmail()) 
      .password(passwordEncoder.encode(request.getPassword()))
      .lastName(request.getLastName())
      .role(request.getRole())
      .build();

    userRepository.save(user);
    return AuthResponse.builder().build();
    
  } 

  public AuthResponse login(AuthRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getEmail(), request.getPassword()
      )
    );
    return AuthResponse.builder().build();
  }



}
