package com.example.projectqlbanhang.Repository;

import com.example.projectqlbanhang.Entity.SignUpToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<SignUpToken,Long> {
    Optional<SignUpToken> findSignUpTokenByToken(String token);
}
