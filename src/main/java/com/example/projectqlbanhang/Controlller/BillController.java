package com.example.projectqlbanhang.Controlller;

import com.example.projectqlbanhang.Entity.Bill;
import com.example.projectqlbanhang.Entity.CartItem;
import com.example.projectqlbanhang.Entity.PaymentRequest;
import com.example.projectqlbanhang.Entity.PaymentStatus;
import com.example.projectqlbanhang.Service.BillService;
import com.example.projectqlbanhang.dto.BillDTO;
import com.example.projectqlbanhang.dto.CartDTO;
import com.example.projectqlbanhang.dto.PaymentRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;
    @PostMapping()
    public ResponseEntity<BillDTO> createBill(@RequestBody CartDTO cartDTO){
       return ResponseEntity.ok(billService.createBill(cartDTO));
    }
    @PostMapping("/pay")
    public ResponseEntity<String> payBill(@RequestBody PaymentRequestDTO paymentRequestDTO){
        return ResponseEntity.ok(billService.payBill(paymentRequestDTO));
    }
}
