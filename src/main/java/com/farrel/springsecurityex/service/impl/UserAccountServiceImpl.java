package com.farrel.springsecurityex.service.impl;

import com.farrel.springsecurityex.entity.UserAccount;
import com.farrel.springsecurityex.repository.UserAccountRepository;
import com.farrel.springsecurityex.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;


    //4. ini penting! buat ngeload username kita untuk di autenticate nanti
    // jadi returnya kasih  entity UserAccount, karena pada project ini yang implement UserDetail adalah UserAccount
    // returnya adalah findByUsername.
    //kalo login pake email ya ganti aja finfByEmail
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountRepository.findByUsername(username);
    }


    @Override
    public UserAccount createUser(UserAccount userAccount) {
        return userAccountRepository.saveAndFlush(userAccount);
    }
}
