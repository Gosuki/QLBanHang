package com.example.projectqlbanhang.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String productImage;
    private double price;
    private int quantity;
    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "product")
    private List<BillDetails> billDetails;

}
