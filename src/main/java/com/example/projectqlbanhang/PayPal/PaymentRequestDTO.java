package com.example.projectqlbanhang.PayPal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class PaymentRequestDTO {
    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;
    private Long billId;
}
