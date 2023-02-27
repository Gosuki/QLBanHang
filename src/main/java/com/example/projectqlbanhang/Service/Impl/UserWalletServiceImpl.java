package com.example.projectqlbanhang.Service.Impl;

import com.example.projectqlbanhang.Convert.UserWalletConvert;
import com.example.projectqlbanhang.Entity.User;
import com.example.projectqlbanhang.Entity.UserWallet;
import com.example.projectqlbanhang.Repository.UserRepository;
import com.example.projectqlbanhang.Repository.UserWalletRepository;
import com.example.projectqlbanhang.Service.UserWalletService;
import com.example.projectqlbanhang.dto.UserWalletDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserWalletServiceImpl implements UserWalletService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserWalletRepository userWalletRepository;
    @Autowired
    private UserWalletConvert userWalletConvert;

    @Override
    public UserWalletDTO createUserWallet(Long userId) {
        User user = userRepository.findUserById(userId);
        if(user == null) {
            return null;
        }
        UserWallet userWallet = new UserWallet();
        userWallet.setUser(user);
        userWallet.setBalance(0);
        UserWallet savedUserWallet =userWalletRepository.save(userWallet);
        return userWalletConvert.userWalletDTO(savedUserWallet);
    }

    @Override
    public UserWalletDTO depositUserWallet(Long userId, double money) {
        UserWallet userWallet = userWalletRepository.findUserWalletByUser_Id(userId);
        User user = userRepository.findUserById(userId);
        if(userWallet == null) {
            return null;
        }
        userWallet.setBalance(money+userWallet.getBalance());
        userWalletRepository.save(userWallet);
        return userWalletConvert.userWalletDTO(userWallet);
    }

    @Override
    public String deleteUserWallet(Long[] userIds) {
        for(Long userId : userIds) {
            if(userWalletRepository.findUserWalletByUser_Id(userId)!=null) {
                userWalletRepository.deleteById(userId);
            }else {
                return "Not found ID";
            }
        }
        return "Delete Successful";
    }
}
