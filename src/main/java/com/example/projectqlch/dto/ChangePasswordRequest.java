package com.example.projectqlch.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String password;
    private String newPassword;
    private String confirmNewPassword;
}
