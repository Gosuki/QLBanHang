package com.example.projectqlch.Controlller;


import com.example.projectqlch.Service.CartService;
import com.example.projectqlch.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{userid}")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public List<CartDTO> addProductToCart(@PathVariable Long userid, @RequestBody CartDTO cartDTO){
        return cartService.addProductToCart(userid, cartDTO);
    }
}
