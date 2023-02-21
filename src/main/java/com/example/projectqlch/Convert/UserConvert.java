package com.example.projectqlch.Convert;

import com.example.projectqlch.Entity.User;
import com.example.projectqlch.dto.UserDTO;
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

        return user;
    }
}
