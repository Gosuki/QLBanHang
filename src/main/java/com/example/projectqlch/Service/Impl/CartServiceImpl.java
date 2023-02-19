package com.example.projectqlch.Service.Impl;

import com.example.projectqlch.Convert.CartConvert;
import com.example.projectqlch.Entity.Cart;
import com.example.projectqlch.Entity.CartItem;
import com.example.projectqlch.Entity.Product;
import com.example.projectqlch.Entity.User;
import com.example.projectqlch.Repository.CartItemRepository;
import com.example.projectqlch.Repository.CartRepository;
import com.example.projectqlch.Repository.ProductRepository;
import com.example.projectqlch.Repository.UserRepository;
import com.example.projectqlch.Service.CartService;
import com.example.projectqlch.Service.ProductService;
import com.example.projectqlch.dto.CartDTO;
import com.example.projectqlch.dto.CartItemRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartConvert cartConvert;


    @Override
    public List<CartDTO> addProductToCart(Long id, CartItemRequest cartItemRequest) {
        User user = userRepository.findUserById(id);

        Product product = productRepository.findProductById(cartItemRequest.getProductId());

        Cart cart = cartRepository.findCartByUserId(user.getId());

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setPrice(product.getPrice()* cartItemRequest.getQuantity());

        List<CartItem> cartItemList = cart.getCartItemList();
        if(cartItemList.isEmpty()){
            cartItemList = new ArrayList<>();
        }
        cartItemList.add(cartItem);
        cart.setCartItemList(cartItemList);

        cartItemRepository.save(cartItem);

        return cartConvert.ToCartDTO(cart);
    }
}