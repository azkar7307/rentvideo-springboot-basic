package com.crio.rentvideo_service.controller.exchange.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
public class AuthResponse {
  private final String message = "Success";
}
