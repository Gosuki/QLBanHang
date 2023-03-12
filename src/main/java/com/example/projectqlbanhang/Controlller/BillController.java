package com.example.projectqlbanhang.Controlller;

import com.example.projectqlbanhang.Repository.BillRepository;
import com.example.projectqlbanhang.Repository.UserRepository;
import com.example.projectqlbanhang.Service.BillService;
import com.example.projectqlbanhang.dto.BaseResponse;
import com.example.projectqlbanhang.dto.BillDTO;
import com.example.projectqlbanhang.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<BillDTO> createBill(@RequestBody CartDTO cartDTO){
       return ResponseEntity.ok(billService.createBill(cartDTO));
    }
    @GetMapping("/all")
    public ResponseEntity<?> allBill(){
        return ResponseEntity.ok(billService.getAllBill());
    }
    @GetMapping("/all")
    public ResponseEntity<?> allBillUser(@RequestParam("username") String username){
        return ResponseEntity.ok(billService.getAllBillUser(username));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBill(@PathVariable(name = "id") Long id,
                                        @RequestParam(name="paymentDate") Date date,
                                        @RequestParam(name="status") String status,
                                        @RequestParam(name="total") double total
                                        ){
        return ResponseEntity.ok(billService.updateBill(id,date,status,total));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBill(@PathVariable(name = "id") Long id){
        billRepository.deleteById(id);
        return ResponseEntity.ok("DELETE");
    }

}
