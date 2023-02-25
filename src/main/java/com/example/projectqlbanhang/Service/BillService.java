package com.example.projectqlbanhang.Service;

import com.example.projectqlbanhang.Entity.Bill;
import com.example.projectqlbanhang.Entity.PaymentRequest;
import com.example.projectqlbanhang.dto.BillDTO;
import com.example.projectqlbanhang.dto.CartDTO;
import com.example.projectqlbanhang.dto.PaymentRequestDTO;

public interface BillService {
    BillDTO createBill(CartDTO cartDTO);

    String payBill(PaymentRequestDTO paymentRequestDTO);
}
