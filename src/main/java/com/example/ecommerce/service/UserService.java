package com.example.ecommerce.service;

import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;

    public User register(User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            throw new RuntimeException("Username and password required");
        }
        return repo.save(user);
    }

    public User login(String username, String password) {
        User user = repo.findByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }
}