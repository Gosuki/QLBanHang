package com.example.projectqlbanhang.Controlller;

import com.example.projectqlbanhang.Service.UserWalletService;
import com.example.projectqlbanhang.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userWallet")
public class UserWalletController {
    @Autowired
    private UserWalletService userWalletService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createUserWallet(@PathVariable(name="userId") Long userId){
        if(userWalletService.createUserWallet(userId)==null){
            return ResponseEntity.badRequest().body("Not found userId: " + userId);
        }
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                userWalletService.createUserWallet(userId),
                "Created userWallet successfully"));

    }
    @PostMapping("/deposit/{userId}")
    public ResponseEntity<?> depositMoney(@PathVariable(name="userId") Long userId,@RequestParam double money){
        if(userWalletService.depositUserWallet(userId, money)==null){
            return ResponseEntity.badRequest().body("Not found userId or UserWallet: ");
        }
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                userWalletService.depositUserWallet(userId, money),
                "DepositMoney into userWallet successfully"));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUserWallet(@RequestParam Long[] userId) {

        return null;
    }
}
