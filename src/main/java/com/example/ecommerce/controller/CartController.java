package com.example.ecommerce.controller;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cart")
@CrossOrigin
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    // 🔥 ADD TO CART
    @PostMapping
    public Cart addToCart(@RequestBody Cart cart) {
        try {
            System.out.println("🔥 Incoming cart: " + cart);
            return service.saveCart(cart);
        } catch (Exception e) {
            e.printStackTrace(); // 🔥 THIS WILL SHOW REAL ERROR
            throw e;
        }
    }

    // 🔥 GET CART
    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable String userId) {

        System.out.println("🔥 GET CART for user: " + userId);

        Cart cart = service.getCartByUserId(userId);

        // 🔥 FIX: NEVER RETURN NULL
        if (cart == null) {
            return new Cart(null, userId, new ArrayList<>());
        }

        return cart;
    }

    // 🔥 REMOVE / DECREASE ITEM
    @DeleteMapping("/{userId}/{productId}")
    public Cart removeItem(
            @PathVariable String userId,
            @PathVariable String productId) {

        System.out.println("🔥 REMOVE product: " + productId);

        return service.removeFromCart(userId, productId);
    }
}