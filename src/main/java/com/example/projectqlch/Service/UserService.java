package com.example.projectqlch.Service;

import com.example.projectqlch.Entity.User;
import com.example.projectqlch.dto.UserDTO;

public interface UserService {
    User signUp(UserDTO userDTO);

    String verifyToken(String token);
    String deleteUser(Long[] ids);

    User updateUser(UserDTO userDTO);
}
