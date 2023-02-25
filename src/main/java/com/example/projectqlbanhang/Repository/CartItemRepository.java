package com.example.projectqlbanhang.Repository;

import com.example.projectqlbanhang.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    CartItem findCartItemById(Long cartItemId);
    CartItem findCartItemByBillDetails_Id(Long billDetailsId);
}
