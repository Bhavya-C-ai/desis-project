package com.example.expensetrackerbackend.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.expensetrackerbackend.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense,Long>{
    List<Expense> findAll();
    @Query("SELECT e.createdAt FROM Expense e ORDER BY e.createdAt DESC")
    Optional<Date> findLastAddedDate();
}
