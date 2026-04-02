package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private String productId;
    private String name;
    private int price;
    private int quantity;
    private String imageUrl; // 🔥 MUST EXIST
}