package com.example.projectqlbanhang.Repository;

import com.example.projectqlbanhang.Entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
    Optional<PaymentEntity> findPaymentRequestByPaymentId(String paymentId);
}
