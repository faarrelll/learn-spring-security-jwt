package com.farrel.springsecurityex.service.impl;

import com.farrel.springsecurityex.constant.Role;
import com.farrel.springsecurityex.entity.UserAccount;
import com.farrel.springsecurityex.dto.LoginRequest;
import com.farrel.springsecurityex.dto.LoginResponse;
import com.farrel.springsecurityex.dto.RegisterRequest;
import com.farrel.springsecurityex.dto.RegisterResponse;
import com.farrel.springsecurityex.repository.UserAccountRepository;
import com.farrel.springsecurityex.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;


    //4. ini penting! buat ngeload username kita untuk di autenticate nanti
    // jadi returnya kasih  entity UserAccount, karena pada project ini yang implement UserDetail adalah UserAccount
    // returnya adalah findByUsername.
    //kalo login pake email ya ganti aja finfByEmail
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountRepository.findByUsername(username);
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {

        UserAccount newUserAccount = UserAccount.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.RROLE_USER)
                .build();

        userAccountRepository.saveAndFlush(newUserAccount);

        return toRegisterResponse(newUserAccount);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

    private static RegisterResponse toRegisterResponse(UserAccount newUserAccount) {
        return RegisterResponse.builder()
                .id(newUserAccount.getId())
                .username(newUserAccount.getUsername())
                .build();
    }
}
