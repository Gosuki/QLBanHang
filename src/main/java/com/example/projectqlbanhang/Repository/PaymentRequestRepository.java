package com.example.projectqlbanhang.Repository;

import com.example.projectqlbanhang.Entity.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRequestRepository extends JpaRepository<PaymentRequest,Long> {
}
