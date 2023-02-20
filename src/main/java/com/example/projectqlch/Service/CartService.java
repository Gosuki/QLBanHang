package com.example.projectqlch.Service;

import com.example.projectqlch.Entity.Cart;
import com.example.projectqlch.Entity.CartItem;
import com.example.projectqlch.Entity.Product;
import com.example.projectqlch.dto.CartDTO;

import java.util.List;

public interface CartService {
    List<CartDTO> addProductToCart(Long id, CartDTO cartDTO);

}
