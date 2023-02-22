package com.example.projectqlch.Controlller;

import com.example.projectqlch.Entity.User;
import com.example.projectqlch.Repository.ProductRepository;
import com.example.projectqlch.Repository.UserRepository;
import com.example.projectqlch.Service.UserService;
import com.example.projectqlch.dto.BaseResponse;
import com.example.projectqlch.dto.ChangePasswordRequest;
import com.example.projectqlch.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;


    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserDTO useDTO){
        if(userRepository.findUsersByUsernameAndActive(useDTO.getUsername(),true) != null ){
            return ResponseEntity.ok(new BaseResponse(HttpStatus.BAD_REQUEST.value(), null,"Username is already taken or Email is already taken!"));
        }else {
            User savedUser = userService.signUp(useDTO);
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), null, "Please check email register"));
        }
    }
    @GetMapping("/verify/{token}")
    public ResponseEntity<BaseResponse> verifyToken(@PathVariable(value = "token") String token){
        String activeMsg= userService.verifyToken(token);
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),null,activeMsg));
    }
    @PutMapping("/update/{UserID}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable(value = "UserID") Long UserID,@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                userService.updateUser(userDTO,UserID),"update Complete"));
    }
    @PutMapping("/update/{UserID}/avatar")
    public ResponseEntity<BaseResponse> updateAvatarUser(@PathVariable(value = "UserID") Long UserID,
                                                         @RequestParam("avatar") MultipartFile avatar ){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                userService.updateAvatarUser(UserID,avatar),
                "update Complete"));
    }
    @PutMapping("/changePassWord/{UserID}")
    public ResponseEntity<BaseResponse> ChangePassword(@PathVariable(value = "UserID") Long UserID, @RequestBody ChangePasswordRequest passwordRequest){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                userService.ChangePassWord(UserID,passwordRequest),
                "Change password complete "));
    }
}
