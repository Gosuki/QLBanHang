package com.example.projectqlbanhang.Service;

import com.example.projectqlbanhang.dto.CartItemDTO;

import java.util.List;

public interface CartService {
    List<CartItemDTO> addProductToCart(Long id, CartItemDTO cartItemDTO);
    String deleteProductFromCart(Long userId, Long productId);


}
