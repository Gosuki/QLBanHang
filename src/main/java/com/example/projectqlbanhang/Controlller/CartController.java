package com.example.projectqlbanhang.Controlller;


import com.example.projectqlbanhang.Service.CartService;
import com.example.projectqlbanhang.dto.BaseResponse;
import com.example.projectqlbanhang.dto.CartItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/{userid}")
    public List<CartItemDTO> addProductToCart(@PathVariable Long userid, @RequestBody CartItemDTO cartItemDTO){
        return cartService.addProductToCart(userid, cartItemDTO);
    }
    @DeleteMapping("/{userid}")
    public ResponseEntity<BaseResponse> deleteProductFromCart(@PathVariable Long userid, @RequestParam Long productId){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                null, cartService.deleteProductFromCart(userid,productId)));
    }
}
