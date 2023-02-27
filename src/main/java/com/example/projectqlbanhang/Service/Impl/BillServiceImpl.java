package com.example.projectqlbanhang.Service.Impl;

import com.example.projectqlbanhang.Entity.*;
import com.example.projectqlbanhang.Repository.*;
import com.example.projectqlbanhang.Service.BillService;
import com.example.projectqlbanhang.dto.BillDTO;
import com.example.projectqlbanhang.dto.CartDTO;
import com.example.projectqlbanhang.dto.CartItemDTO;
import com.example.projectqlbanhang.dto.PaymentRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService {
    private final UserRepository userRepository;
    private final BillRepository billRepository;
    private final CartItemRepository cartItemRepository;
    private final UserWalletRepository userWalletRepository;
    private final BillDetailsRepository billDetailsRepository;
    private final PaymentRequestRepository paymentRequestRepository;

    @Override
    public BillDTO createBill(CartDTO cartDTO) {
        User user = userRepository.findUserById(cartDTO.getUserId());
        Long[] cardItemIds = cartDTO.getCartItemsIds();
        List<CartItem> cartItemList = new ArrayList<>();
        for (Long cartItemId : cardItemIds){
            cartItemList.add(cartItemRepository.findCartItemById(cartItemId));
        }

        Bill bill = new Bill();
        bill.setUser(user);
        bill.setStatus(PaymentStatus.PENDING);
        bill.setSum(getTotalPrice(cartItemList));
        billRepository.save(bill);
        for (int i=0; i<cartItemList.size(); i++){
            BillDetails billDetails = new BillDetails();
            billDetails.setBill(bill);
            billDetails.setProduct(cartItemList.get(i).getProduct());
            billDetailsRepository.save(billDetails);
            cartItemList.get(i).setBillDetails(billDetails);
            cartItemRepository.save(cartItemList.get(i));
        }
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setUser(user);
        paymentRequest.setStatus(PaymentStatus.PENDING);
        paymentRequest.setAmount(getTotalPrice(cartItemList));
        paymentRequest.setBillPayment(bill);
        paymentRequestRepository.save(paymentRequest);

        BillDTO billDTO = new BillDTO();
        billDTO.setUsername(bill.getUser().getUsername());
        billDTO.setUserAddress(bill.getUser().getAddress());
        billDTO.setUserEmail(bill.getUser().getEmail());
        billDTO.setUserPhoneNumber(bill.getUser().getPhone());
        billDTO.setCartId(cartDTO.getId());
        billDTO.setPaymentStatus(String.valueOf(bill.getStatus()));
        billDTO.setCartDTO(cartDTO);
        return billDTO;
    }

    @Override
    public String payBill(PaymentRequestDTO paymentRequestDTO) {
        User user = userRepository.findUserById(paymentRequestDTO.getUserId());
        Bill bill = billRepository.findBillById(paymentRequestDTO.getBillId());
        UserWallet userWallet = user.getUserWalletList();
        if(userWallet.getBalance() < bill.getSum())
        {
            return null;
        }
        userWallet.setBalance(userWallet.getBalance()-bill.getSum());
        userWalletRepository.save(userWallet);
        bill.setStatus(PaymentStatus.SUCCESS);
        bill.setPayDate(new Date());
        billRepository.save(bill);

        List<BillDetails> billDetailsList = bill.getBillDetails();
        for (BillDetails billDetails : billDetailsList){
            CartItem cartItem = cartItemRepository.findCartItemById(billDetails.getId());
            cartItemRepository.delete(cartItem);
        }
        bill.getPaymentRequest().setStatus(PaymentStatus.SUCCESS);
        return "SUCCESS";
    }

    public double getTotalPrice(List<CartItem> cartItemList) {
        double totalPrice=0;
        for (CartItem cartItem:cartItemList){
            totalPrice=+cartItem.getPrice();
        }
        return totalPrice;
    }
}
