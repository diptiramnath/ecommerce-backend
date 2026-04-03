package com.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {
    public static void main(String[] args) {
        System.setProperty(
                "spring.data.mongodb.uri",
                "mongodb+srv://dipti05:arvi@ecommerce-db.klslrnb.mongodb.net/ecommerceDB"
        );
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
