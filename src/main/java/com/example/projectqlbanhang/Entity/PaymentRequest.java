package com.example.projectqlbanhang.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class PaymentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "bill_id")
    private Bill billPayment;

    @Column(name = "amount")
    private double amount;

    @Column(name = "description")
    private String description;

    @Column(name = "transaction_id")
    private String transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
