package com.example.projectqlbanhang.Repository;

import com.example.projectqlbanhang.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findCartByUserId(Long userId);
}
