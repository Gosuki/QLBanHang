package com.example.projectqlch.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double sum;
    private Date orderDate;
    private String status;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;

    @OneToMany(mappedBy ="bill")
    private List<DetailedInvoice> bills;

}
