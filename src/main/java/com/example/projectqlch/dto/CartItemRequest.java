package com.example.projectqlch.dto;

import lombok.Data;

@Data
public class CartItemRequest {
    private Long productId;
    private int  quantity;
}
