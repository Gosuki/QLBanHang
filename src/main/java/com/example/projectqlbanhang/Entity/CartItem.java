package com.example.projectqlbanhang.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    private int quantityProduct;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "billdetail_id")
    private BillDetails billDetails;
}
