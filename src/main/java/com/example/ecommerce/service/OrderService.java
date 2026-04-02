package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    // 🔥 PLACE ORDER (FIXED)
    public Order placeOrder(String userId) {

        System.out.println("🔥 Placing order for user: " + userId);

        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }

        if (cart.getProducts().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double total = cart.getProducts()
                .stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();

        Order order = new Order();
        order.setUserId(userId);
        order.setProducts(cart.getProducts());
        order.setTotal(total);
        order.setStatus("PLACED");
        order.setCreatedAt(LocalDateTime.now().toString());

        // 🔥 SAVE TO DB
        Order savedOrder = orderRepository.save(order);

        System.out.println("✅ Order saved: " + savedOrder.getId());

        // 🔥 CLEAR CART
        cart.getProducts().clear();
        cartRepository.save(cart);

        return savedOrder;
    }

    // 🔥 GET ORDERS
    public List<Order> getOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }

    // 🔥 DELETE ORDER (FIXED)
    public void deleteOrder(String orderId) {

        System.out.println("🔥 Deleting order: " + orderId);

        Order order = orderRepository.findById(orderId).orElse(null);

        if (order == null) {
            System.out.println("❌ Order NOT FOUND");
            return;
        }

        orderRepository.delete(order);

        System.out.println("✅ Deleted from DB");
    }
}