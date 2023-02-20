package com.example.projectqlch.Controlller;


import com.example.projectqlch.Service.CartService;
import com.example.projectqlch.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{id}")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public List<CartDTO> addProductToCart(@PathVariable Long id, @RequestBody CartDTO cartDTO){
        return cartService.addProductToCart(id, cartDTO);
    }
}
