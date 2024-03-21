package com.example.expensetrackerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.expensetrackerbackend.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    // You can add custom methods here if needed
    User findByEmail(String email);
}