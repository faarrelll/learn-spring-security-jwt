package com.farrel.springsecurityex.repository;

import com.farrel.springsecurityex.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;


//2, Jangan lupa Repo
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    UserAccount findByUsername(String username);
}
