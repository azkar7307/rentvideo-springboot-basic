package com.crio.rentvideo_service.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.crio.rentvideo_service.service.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

  @Autowired
  UserService userService;

  @Bean
  SecurityFilterChain sequrityFilterChain(HttpSecurity httpSecurity) throws Exception {
    // disable the CSRF to test from localhost and Postman.
    httpSecurity.csrf(csrf -> csrf.disable());

    httpSecurity.authenticationProvider(authenticationProvider());

    httpSecurity.authorizeHttpRequests(configurer -> configurer
      .requestMatchers( "/api/login", "/api/register")
      .permitAll()

      .requestMatchers(HttpMethod.GET, "/api/videos")
      .hasAnyRole("CUSTOMER", "ADMIN")
      
      .requestMatchers(HttpMethod.POST, "/api/videos")
      .hasRole("ADMIN")
      
      .requestMatchers("/api/videos/**")
      .hasRole("ADMIN")
      
      .anyRequest()
      .authenticated()
      );

      httpSecurity.httpBasic(Customizer.withDefaults());

      return httpSecurity.build();
  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userService);

    // daoAuthenticationProvider.setUserDetailsService(userService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
}
