package com.example.expensetrackerbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensetrackerbackend.entity.Income;

public interface IncomeRepository extends JpaRepository<Income, Long>{
    List<Income> findAllByOrderByCreatedAtDesc();

}
