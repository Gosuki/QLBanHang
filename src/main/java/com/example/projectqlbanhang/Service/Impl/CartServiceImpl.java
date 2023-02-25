package com.example.projectqlbanhang.Service.Impl;

import com.example.projectqlbanhang.Convert.CartConvert;
import com.example.projectqlbanhang.Entity.Cart;
import com.example.projectqlbanhang.Entity.CartItem;
import com.example.projectqlbanhang.Entity.Product;
import com.example.projectqlbanhang.Repository.CartItemRepository;
import com.example.projectqlbanhang.Repository.CartRepository;
import com.example.projectqlbanhang.Repository.ProductRepository;
import com.example.projectqlbanhang.Repository.UserRepository;
import com.example.projectqlbanhang.Service.CartService;
import com.example.projectqlbanhang.dto.CartItemDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public List<CartItemDTO> addProductToCart(Long id, CartItemDTO cartItemDTO) {

        Product product = productRepository.findProductById(cartItemDTO.getIdProduct());
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
            cartItem.setPrice(product.getPrice() * cartItemDTO.getQuantityProduct());
            cartItem.setQuantityProduct(cartItemDTO.getQuantityProduct());
        }else {
            cartItem.setQuantityProduct(cartItemDTO.getQuantityProduct()+cartItem.getQuantityProduct());
            cartItem.setPrice(product.getPrice() * cartItem.getQuantityProduct());
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

    @Override
    public String deleteProductFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findCartByUserId(userId);
        Product product = productRepository.findProductById(productId);
        CartItem cartItem = getCartItemByCartAndProduct(cart,product);
        cartItemRepository.delete(Objects.requireNonNull(cartItem));
        return "DELETE " + product.getName();
    }
}