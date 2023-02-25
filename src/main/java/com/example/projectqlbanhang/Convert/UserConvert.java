package com.example.projectqlbanhang.Convert;

import com.example.projectqlbanhang.Entity.User;
import com.example.projectqlbanhang.dto.UserDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserConvert {
    public UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setFullName(user.getName());
        dto.setActive(user.isActive());
        dto.setAddress(user.getAddress());
        dto.setImageUrl(user.getImageUrl());
        return dto;
    }
    public User toEntityUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getFullName());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole());
        user.setActive(userDTO.isActive());
        user.setAddress(userDTO.getAddress());

        return user;
    }
}
