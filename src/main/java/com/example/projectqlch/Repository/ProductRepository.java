package com.example.projectqlch.Repository;

import com.example.projectqlch.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value="select u from Product u where u.productImage like %:name AND u.id=:id")
    Product findProductByProductImageAndId(String name,Long id);

    Product findProductById(Long id);
}
