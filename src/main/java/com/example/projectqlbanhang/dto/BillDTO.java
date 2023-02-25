package com.example.projectqlbanhang.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private Long cartId;
    private String username;
    private String userEmail;
    private String userPhoneNumber;
    private String userAddress;
    private String paymentStatus;
    private CartDTO cartDTO;
}
