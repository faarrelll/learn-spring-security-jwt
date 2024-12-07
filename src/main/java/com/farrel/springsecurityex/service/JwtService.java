package com.farrel.springsecurityex.service;

import com.farrel.springsecurityex.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(String username);

    String extractUsername(String jwtToken);

    boolean validateToken(String jwtToken, UserDetails userAccount);
}
