package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository repo;

    public Cart saveCart(Cart newCart) {

        Cart existing = repo.findByUserId(newCart.getUserId());

        if (existing != null) {

            for (CartItem newItem : newCart.getProducts()) {

                boolean found = false;

                for (CartItem item : existing.getProducts()) {

                    if (item.getProductId().equals(newItem.getProductId())) {
                        item.setQuantity(item.getQuantity() + 1); // 🔥 increment
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    newItem.setQuantity(1);
                    existing.getProducts().add(newItem);
                }
            }

            return repo.save(existing);
        }

        // new cart
        newCart.getProducts().forEach(i -> i.setQuantity(1));
        return repo.save(newCart);
    }

    public Cart getCartByUserId(String userId) {

        Cart cart = repo.findByUserId(userId);

        if (cart == null) {
            // 🔥 RETURN EMPTY CART INSTEAD OF NULL
            return new Cart(null, userId, new ArrayList<>());
        }

        return cart;
    }

    public Cart removeFromCart(String userId, String productId) {

        Cart cart = repo.findByUserId(userId);

        for (CartItem item : cart.getProducts()) {
            if (item.getProductId().equals(productId)) {

                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1); // 🔥 decrement
                } else {
                    cart.getProducts().remove(item); // remove fully
                }

                break;
            }
        }

        return repo.save(cart);
    }
}