package com.example.expensetrackerbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.expensetrackerbackend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
    List<User> findByEmail(String email);
}
