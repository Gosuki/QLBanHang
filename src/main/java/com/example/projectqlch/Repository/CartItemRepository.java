package com.example.projectqlch.Repository;

import com.example.projectqlch.Entity.Cart;
import com.example.projectqlch.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}
