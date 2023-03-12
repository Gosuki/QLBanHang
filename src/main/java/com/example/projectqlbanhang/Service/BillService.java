package com.example.projectqlbanhang.Service;

import com.example.projectqlbanhang.dto.BillDTO;
import com.example.projectqlbanhang.dto.CartDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BillService {
    BillDTO createBill(CartDTO cartDTO);

    List<BillDTO> getAllBill();

    List<BillDTO> getAllBillUser(String username);


    BillDTO updateBill(Long id, Date date, String status, double total);
}
