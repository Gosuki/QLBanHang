package com.example.projectqlch.Convert;

import com.example.projectqlch.Entity.Cart;
import com.example.projectqlch.Entity.CartItem;
import com.example.projectqlch.dto.CartDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartConvert {

    public List<CartDTO> ToCartDTO(Cart cart) {
        List<CartDTO> result = new ArrayList<>();
        List<CartItem> list = cart.getCartItemList();
        for (CartItem cartItem : list) {
            CartDTO item = new CartDTO();
            item.setNameProduct(cartItem.getProduct().getName());
            item.setPrice(cartItem.getPrice()*cartItem.getQuantityProduct());
            item.setDescriptionProduct(cartItem.getProduct().getDescription());
            item.setQuantityProduct(cartItem.getQuantityProduct());
            item.setIdProduct(cartItem.getProduct().getId());
            result.add(item);
        }
        return result;
    }
}
