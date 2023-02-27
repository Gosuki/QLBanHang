package com.example.projectqlbanhang.Controlller;

import com.example.projectqlbanhang.Convert.UserWalletConvert;
import com.example.projectqlbanhang.Entity.UserWallet;
import com.example.projectqlbanhang.Repository.UserWalletRepository;
import com.example.projectqlbanhang.Service.UserWalletService;
import com.example.projectqlbanhang.dto.BaseResponse;
import com.example.projectqlbanhang.dto.UserWalletDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userWallet")
public class UserWalletController {
    @Autowired
    private UserWalletService userWalletService;
    @Autowired
    private UserWalletRepository userWalletRepository;
    @Autowired
    private UserWalletConvert userWalletConvert;

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
    public String deleteUserWallet(@RequestParam Long[] userIds) {
        return userWalletService.deleteUserWallet(userIds);
    }
    @GetMapping("/get")
    public List<UserWalletDTO> getUserWallet(@RequestParam Long[] userIds) {
        List<UserWalletDTO> userWalletDTOList = new ArrayList<>();
        for (Long userId : userIds) {
            if(userWalletRepository.findUserWalletByUser_Id(userId)!=null) {
                userWalletDTOList.add(userWalletConvert.userWalletDTO(userWalletRepository.findUserWalletByUser_Id(userId)));
            }
        }
        return userWalletDTOList;
    }
}
