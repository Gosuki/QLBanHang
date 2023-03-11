package com.example.projectqlbanhang.PayPal;

import com.example.projectqlbanhang.Entity.Bill;
import com.example.projectqlbanhang.Entity.BillDetails;
import com.example.projectqlbanhang.Entity.PaymentEntity;
import com.example.projectqlbanhang.Repository.BillRepository;
import com.example.projectqlbanhang.Repository.CartItemRepository;
import com.example.projectqlbanhang.Repository.PaymentRepository;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.projectqlbanhang.Entity.PaymentStatus.PENDING;
import static com.example.projectqlbanhang.Entity.PaymentStatus.SUCCESS;

@Controller
public class PaypalController {
    @Autowired
    PaypalService service;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @PostMapping("/pay")
    public ResponseEntity<?> payment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        try {
            Payment payment = service.createPayment(paymentRequestDTO.getPrice(), paymentRequestDTO.getCurrency(), paymentRequestDTO.getMethod(),
                    paymentRequestDTO.getIntent(), paymentRequestDTO.getDescription(), "http://localhost:8088/" + CANCEL_URL,
                    "http://localhost:8088/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    PaymentEntity paymentEntity = new PaymentEntity();
                    paymentEntity.setPaymentId(payment.getId());
                    Bill bill = billRepository.findBillById(paymentRequestDTO.getBillId());
                    paymentEntity.setBillPayment(bill);
                    paymentEntity.setIntent(payment.getIntent());
                    paymentEntity.setTotalPayment(paymentRequestDTO.getPrice());
                    paymentEntity.setDescription(paymentRequestDTO.getDescription());
                    paymentEntity.setCurrency(paymentRequestDTO.getCurrency());
                    paymentEntity.setMethod(paymentRequestDTO.getMethod());
                    paymentEntity.setStatus(PENDING);
                    paymentRepository.save(paymentEntity);
                    return ResponseEntity.ok(link.getHref());
                }
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create payment");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create payment");
    }
    @GetMapping(value = CANCEL_URL)
    public ResponseEntity<?> cancelPay() {
        return ResponseEntity.ok("Payment canceled");
    }

    @GetMapping(value = SUCCESS_URL)
    public ResponseEntity<?> successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            //System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                Optional<PaymentEntity> optionalPayment = paymentRepository.findPaymentRequestByPaymentId(payment.getId());
                if (optionalPayment.isPresent()) {
                    PaymentEntity paymentEntity = optionalPayment.get();
                    paymentEntity.setStatus(SUCCESS);
                    Bill bill = paymentEntity.getBillPayment();
                    bill.setPayDate(new Date());
                    bill.setPaymentEntity(paymentEntity);
                    bill.setStatus(SUCCESS);
                    billRepository.save(bill);
                    List<BillDetails> billDetails=bill.getBillDetails();
                    for (BillDetails billDetail : billDetails) {
                        cartItemRepository.deleteAllByBillDetails(billDetail);
                    }
                }
                return ResponseEntity.ok("Payment successful");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to execute payment");
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to execute payment");
        }
    }

}
