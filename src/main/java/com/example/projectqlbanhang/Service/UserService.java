package com.example.projectqlbanhang.Service;

import com.example.projectqlbanhang.Entity.User;
import com.example.projectqlbanhang.dto.ChangePasswordRequest;
import com.example.projectqlbanhang.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User signUp(UserDTO userDTO);

    String verifyToken(String token);
    String deleteUser(Long[] ids);

    UserDTO updateUser(UserDTO userDTO,Long userId);

    UserDTO updateAvatarUser(Long userId, MultipartFile avatar);
    UserDTO ChangePassWord(Long userId, ChangePasswordRequest changePasswordRequest);
}
