package com.example.projectqlbanhang.Repository;

import com.example.projectqlbanhang.Entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    Bill findBillById(Long id);
}