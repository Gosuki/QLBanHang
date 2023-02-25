package com.example.projectqlbanhang.Convert;

import com.example.projectqlbanhang.Entity.Cart;
import com.example.projectqlbanhang.Entity.CartItem;
import com.example.projectqlbanhang.dto.CartItemDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartConvert {

    public List<CartItemDTO> ToCartDTO(Cart cart) {
        List<CartItemDTO> result = new ArrayList<>();
        List<CartItem> list = cart.getCartItemList();
        for (CartItem cartItem : list) {
            CartItemDTO item = new CartItemDTO();
            item.setNameProduct(cartItem.getProduct().getName());
            item.setPrice(cartItem.getPrice());
            item.setDescriptionProduct(cartItem.getProduct().getDescription());
            item.setQuantityProduct(cartItem.getQuantityProduct());
            item.setIdProduct(cartItem.getProduct().getId());
            item.setCartItemId(cartItem.getId());
            result.add(item);
        }
        return result;
    }
}
