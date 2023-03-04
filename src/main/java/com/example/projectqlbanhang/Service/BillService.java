package com.example.projectqlbanhang.Service;

import com.example.projectqlbanhang.dto.BillDTO;
import com.example.projectqlbanhang.dto.CartDTO;

public interface BillService {
    BillDTO createBill(CartDTO cartDTO);

}
