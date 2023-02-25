package com.example.projectqlbanhang.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String password;
    private String newPassword;
    private String confirmNewPassword;
}
