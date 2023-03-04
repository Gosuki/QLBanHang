package com.example.projectqlbanhang.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentId;
    @OneToOne
    @JoinColumn(name = "bill_id")
    private Bill billPayment;

    @Column(name = "total_payment")
    private double totalPayment;

    @Column(name = "currency")
    private String currency;

    @Column(name = "method")
    private String method;

    @Column(name = "intent")
    private String intent;

    @Column(name = "description")
    private String description;

//    @Column(name = "transaction_id")
//    private String transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
