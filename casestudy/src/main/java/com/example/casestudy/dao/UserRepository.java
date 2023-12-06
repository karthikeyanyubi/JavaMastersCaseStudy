package com.example.casestudy.dao;

import com.example.casestudy.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserAccount,Integer> {
    Optional<UserAccount> findByLoginId(String loginId);

    Optional<UserAccount> findByToken(String token);
}
