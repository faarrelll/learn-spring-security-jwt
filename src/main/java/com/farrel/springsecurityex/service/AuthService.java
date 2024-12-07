package com.farrel.springsecurityex.service;

import com.farrel.springsecurityex.dto.LoginRequest;
import com.farrel.springsecurityex.dto.LoginResponse;
import com.farrel.springsecurityex.dto.RegisterRequest;
import com.farrel.springsecurityex.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
