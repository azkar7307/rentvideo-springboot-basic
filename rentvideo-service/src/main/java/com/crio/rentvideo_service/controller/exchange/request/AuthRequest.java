package com.crio.rentvideo_service.controller.exchange.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequest {
  
  private String email;
  private String password;
  
}
