package com.example.projectqlbanhang.Convert;

import com.example.projectqlbanhang.Entity.Bill;
import com.example.projectqlbanhang.dto.BillDTO;
import org.springframework.stereotype.Component;

@Component
public class BillConvert {
    public BillDTO toBillDTO(Bill bill) {
        BillDTO billdto = new BillDTO();
        billdto.setUsername(bill.getUser().getUsername());
        billdto.setUserAddress(bill.getUser().getAddress());
        billdto.setUserEmail(bill.getUser().getEmail());
        billdto.setUserPhoneNumber(bill.getUser().getPhone());
        billdto.setPaymentStatus(String.valueOf(bill.getStatus()));
        billdto.setId(bill.getId());
        billdto.setUser_id(bill.getUser().getId());
        billdto.setStatus(bill.getStatus().toString());
        billdto.setPaymentDate(bill.getPayDate());
        billdto.setSum(bill.getSum());
        return billdto;
    }
}
