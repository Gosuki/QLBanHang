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
import com.example.projectqlch.dto.CartDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartConvert cartConvert;


    public static CartItem getCartItemByCartAndProduct(Cart cart,Product product){
        List<CartItem> temp = cart.getCartItemList();
        for(CartItem item : temp){
            if(item.getProduct()==product) {
                return item;
            }
        }
        return null;
    }
    @Override
    public List<CartDTO> addProductToCart(Long id, CartDTO cartDTO) {

        Product product = productRepository.findProductById(cartDTO.getIdProduct());
        Cart cart = cartRepository.findCartByUserId(id);
        if(cart == null){
            cart = new Cart();
            cart.setUser(userRepository.findUserById(id));
            cartRepository.save(cart);
        }

        CartItem cartItem = getCartItemByCartAndProduct(cart,product);
        if(cartItem==null){
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setPrice(product.getPrice()* cartDTO.getQuantityProduct());
            cartItem.setQuantityProduct(cartDTO.getQuantityProduct());
        }else {
            cartItem.setQuantityProduct(cartDTO.getQuantityProduct()+cartItem.getQuantityProduct());
        }
        List<CartItem> cartItemList = cart.getCartItemList();
        if(cartItemList.isEmpty()){
            cartItemList = new ArrayList<>();
            cartItemList.add(cartItem);
        }
        cart.setCartItemList(cartItemList);
        cartItemRepository.save(cartItem);
        return cartConvert.ToCartDTO(cart);
    }
}