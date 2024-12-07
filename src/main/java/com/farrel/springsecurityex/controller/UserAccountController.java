package com.farrel.springsecurityex.controller;


import com.farrel.springsecurityex.dto.RegisterRequest;
import com.farrel.springsecurityex.dto.RegisterResponse;
import com.farrel.springsecurityex.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping(path = "/auth/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        return userAccountService.register(registerRequest);
    }
}
