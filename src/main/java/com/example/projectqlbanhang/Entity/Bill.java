package com.example.projectqlbanhang.Entity;

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
    private double sum;
    private Date payDate;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy ="billPayment")
    private PaymentRequest paymentRequest;

    @OneToMany(mappedBy ="bill")
    private List<BillDetails> billDetails;
}
