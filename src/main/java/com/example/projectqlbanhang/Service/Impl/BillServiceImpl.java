package com.example.projectqlbanhang.Service.Impl;

import com.example.projectqlbanhang.Convert.BillConvert;
import com.example.projectqlbanhang.Entity.*;
import com.example.projectqlbanhang.Repository.*;
import com.example.projectqlbanhang.Service.BillService;
import com.example.projectqlbanhang.dto.BillDTO;
import com.example.projectqlbanhang.dto.CartDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService {
    private final UserRepository userRepository;
    private final BillRepository billRepository;
    private final CartItemRepository cartItemRepository;
    private final BillDetailsRepository billDetailsRepository;
    private final PaymentRepository paymentRepository;
    private final BillConvert billConvert;

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
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setStatus(PaymentStatus.PENDING);
        paymentEntity.setTotalPayment(getTotalPrice(cartItemList));
        paymentEntity.setBillPayment(bill);
        paymentRepository.save(paymentEntity);

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
    public List<BillDTO> getAllBill() {
        List<Bill> billList=billRepository.findAll();
        List<BillDTO> billDTO = new ArrayList<>();
        for(Bill bill : billList){
            billDTO.add(billConvert.toBillDTO(bill));
        }
        return billDTO;
    }

    @Override
    public List<BillDTO> getAllBillUser(String username) {
        User user = userRepository.findUsersByUsernameAndActive(username,true);
        List<Bill> billList=billRepository.findBillsByUser_IdAndStatus(user.getId(),PaymentStatus.SUCCESS);
        List<BillDTO> billDTO = new ArrayList<>();
        for(Bill bill : billList){
            billDTO.add(billConvert.toBillDTO(bill));
        }
        return billDTO;
    }

    @Override
    public BillDTO updateBill(Long id, Date date, String status, double total) {
        Bill bill = billRepository.findBillById(id);
        bill.setSum(total);
        bill.setStatus(PaymentStatus.valueOf(status));
        bill.setPayDate(date);
        billRepository.save(bill);
        return billConvert.toBillDTO(bill);
    }

    public double getTotalPrice(List<CartItem> cartItemList) {
        double totalPrice=0;
        for (CartItem cartItem:cartItemList){
            totalPrice=+cartItem.getPrice();
        }
        return totalPrice;
    }
}
