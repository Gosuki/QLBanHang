package com.example.projectqlbanhang.Repository;

import com.example.projectqlbanhang.Entity.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWalletRepository extends JpaRepository<UserWallet, Long> {
}