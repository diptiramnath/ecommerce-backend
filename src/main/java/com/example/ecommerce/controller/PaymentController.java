package com.example.ecommerce.controller;

import com.example.ecommerce.model.PaymentRequest;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/pay")
    public String makePayment(@RequestBody PaymentRequest request) {

        // 🔹 Dummy payment logic (always success)
        boolean paymentSuccess = true;

        if (paymentSuccess) {
            var order = orderRepository.findById(request.getOrderId()).orElse(null);
            if (order != null) {
                order.setPaymentStatus("PAID");
                orderRepository.save(order);
            }
            return "Payment Successful";
        } else {
            return "Payment Failed";
        }
    }
}