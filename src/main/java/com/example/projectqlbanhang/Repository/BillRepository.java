package com.example.projectqlbanhang.Repository;

import com.example.projectqlbanhang.Entity.Bill;
import com.example.projectqlbanhang.Entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
   @Query(value = "select u from Bill u where u.id=:id")
   Bill findBillById(Long id);

   List<Bill> findBillsByUser_IdAndStatus(Long userId, PaymentStatus paymentStatus);
}