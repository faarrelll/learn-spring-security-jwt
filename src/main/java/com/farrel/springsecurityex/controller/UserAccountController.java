package com.farrel.springsecurityex.controller;


import com.farrel.springsecurityex.dto.LoginRequest;
import com.farrel.springsecurityex.dto.LoginResponse;
import com.farrel.springsecurityex.dto.RegisterRequest;
import com.farrel.springsecurityex.dto.RegisterResponse;
import com.farrel.springsecurityex.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAccountController {

    private final AuthService authService;

    @PostMapping(path = "/auth/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping(path = "/auth/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
