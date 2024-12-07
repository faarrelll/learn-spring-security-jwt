package com.farrel.springsecurityex.service.impl;


import com.farrel.springsecurityex.constant.Role;
import com.farrel.springsecurityex.dto.LoginRequest;
import com.farrel.springsecurityex.dto.LoginResponse;
import com.farrel.springsecurityex.dto.RegisterRequest;
import com.farrel.springsecurityex.dto.RegisterResponse;
import com.farrel.springsecurityex.entity.UserAccount;
import com.farrel.springsecurityex.service.AuthService;
import com.farrel.springsecurityex.service.JwtService;
import com.farrel.springsecurityex.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserAccountService userAccountService;
    private final JwtService jwtService;
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {

        UserAccount newUserAccount = UserAccount.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.RROLE_USER)
                .build();

        return toRegisterResponse(userAccountService.createUser(newUserAccount));
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return LoginResponse.builder()
                    .token(jwtService.generateToken(loginRequest.getUsername()))
                    .build();
        }
        throw new UsernameNotFoundException("Invalid username or password");
//        return null;
    }


    private static RegisterResponse toRegisterResponse(UserAccount newUserAccount) {
        return RegisterResponse.builder()
                .id(newUserAccount.getId())
                .username(newUserAccount.getUsername())
                .build();
    }
}
