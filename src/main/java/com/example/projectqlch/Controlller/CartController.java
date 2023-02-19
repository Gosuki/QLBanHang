package com.example.projectqlch.Controlller;


import com.example.projectqlch.Entity.Cart;
import com.example.projectqlch.Service.CartService;
import com.example.projectqlch.dto.CartDTO;
import com.example.projectqlch.dto.CartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{id}")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public List<CartDTO> addProductToCart(@PathVariable Long id, @RequestBody CartItemRequest cartItemRequest){
        return cartService.addProductToCart(id, cartItemRequest);
    }
}
