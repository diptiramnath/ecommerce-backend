package com.example.ecommerce.controller;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 🔥 PLACE ORDER
    @PostMapping("/{userId}")
    public Order placeOrder(@PathVariable String userId) {
        return orderService.placeOrder(userId);
    }

    // 🔥 GET ORDERS
    @GetMapping("/{userId}")
    public List<Order> getOrders(@PathVariable String userId) {
        return orderService.getOrders(userId);
    }

    // 🔥 DELETE ORDER
    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
        return "Deleted successfully";
    }
}