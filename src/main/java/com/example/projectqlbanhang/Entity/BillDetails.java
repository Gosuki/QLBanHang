package com.example.projectqlbanhang.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "billDetails")
public class BillDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy ="billDetails")
    private List<CartItem> cartItems;


}
