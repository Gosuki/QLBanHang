package com.example.projectqlbanhang.Convert;

import com.example.projectqlbanhang.Entity.UserWallet;
import com.example.projectqlbanhang.dto.UserWalletDTO;
import org.springframework.stereotype.Component;

@Component
public class UserWalletConvert {
    public UserWalletDTO userWalletDTO(UserWallet userWallet) {
        UserWalletDTO dto = new UserWalletDTO();
        dto.setUserName(userWallet.getUser().getName());
        dto.setBalance(userWallet.getBalance());
        return dto;
    }
}
