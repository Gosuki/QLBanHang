package com.example.projectqlch.dto;

import com.example.projectqlch.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDTO {
    @NotEmpty(message = "Thieu fullName")
    private String fullName;
    @NotEmpty(message = "thieu user")
    private String username;
    @Email(message = "Email khong hop le")
    private String email;
    @NotEmpty(message = "thieu so phone")
    @Min(value = 9,message = "so phone 9 ki tu tro len")
    private String phone;
    @NotEmpty(message = "thieu password")
    @Min(value = 8,message = "password 8 ki tu tro len")
    private String password;
    private Role role;
    private boolean active;
    private String address;
    private String imageUrl;
}
