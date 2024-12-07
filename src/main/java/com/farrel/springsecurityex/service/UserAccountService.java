package com.farrel.springsecurityex.service;

import com.farrel.springsecurityex.dto.LoginRequest;
import com.farrel.springsecurityex.dto.LoginResponse;
import com.farrel.springsecurityex.dto.RegisterRequest;
import com.farrel.springsecurityex.dto.RegisterResponse;
import com.farrel.springsecurityex.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;


//3. Buat Service Dengan extend ke UserDetaiService
// kalo langsung implement ya extend
public interface UserAccountService extends UserDetailsService {
    UserAccount createUser(UserAccount userAccount);
}
