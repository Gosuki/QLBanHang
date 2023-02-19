package com.example.projectqlch.Convert;

import com.example.projectqlch.Entity.Cart;
import com.example.projectqlch.Entity.CartItem;
import com.example.projectqlch.dto.CartDTO;

import java.util.ArrayList;
import java.util.List;

public class CartConvert {

    public List<CartDTO> ToCartDTO(Cart cart) {
        List<CartDTO> result = new ArrayList<>();
        List<CartItem> list = cart.getCartItemList();
        for (CartItem cartItem : list) {
            CartDTO item = new CartDTO();
            item.setNameProduct(cartItem.getProduct().getName());
            item.setPrice(cartItem.getPrice());
            item.setDescriptionProduct(cartItem.getProduct().getDescription());
            item.setQuantityProduct(cartItem.getProduct().getQuantity());
            result.add(item);
        }
        return result;
    }
}
