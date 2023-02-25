package com.example.projectqlbanhang.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private Long id;
    private Long userId;
    private List<CartItemDTO> cartItems;
}
