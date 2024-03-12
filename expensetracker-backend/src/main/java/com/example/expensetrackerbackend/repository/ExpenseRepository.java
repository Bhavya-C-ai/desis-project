package com.example.expensetrackerbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensetrackerbackend.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense,Long>{
    List<Expense> findAll();
}
