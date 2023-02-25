package com.example.projectqlbanhang.dto;

import com.example.projectqlbanhang.Entity.PaymentStatus;
import lombok.Data;

@Data
public class PaymentRequestDTO {
    private Long billId;
    private Long userId;
    private double amount;
    private String description;
    private String status;
}
