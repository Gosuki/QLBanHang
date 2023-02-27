package com.example.projectqlbanhang.Service;

import com.example.projectqlbanhang.dto.UserWalletDTO;

public interface UserWalletService {
    UserWalletDTO createUserWallet(Long userId);
    UserWalletDTO depositUserWallet(Long userId,double money);
    String deleteUserWallet(Long[] userIds);
}
