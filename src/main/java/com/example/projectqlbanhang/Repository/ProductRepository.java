package com.example.projectqlbanhang.Repository;

import com.example.projectqlbanhang.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value="select u from Product u where u.productImage like %:name AND u.id=:id")
    Product findProductByProductImageAndId(String name,Long id);

    Product findProductById(Long id);
    @Query(value="select u from Product u")
    List<Product> findAllProducts();
    @Query(value="select u from Product u where u.name like %:name%")
    List<Product> searchProductByNameLike(String name);
}
