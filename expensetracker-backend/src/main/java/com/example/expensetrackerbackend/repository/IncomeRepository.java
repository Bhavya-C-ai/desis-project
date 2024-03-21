package com.example.expensetrackerbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.expensetrackerbackend.entity.Income;

@EnableJpaRepositories
public interface IncomeRepository extends JpaRepository<Income, Long>{
    List<Income> findAllByOrderByCreatedAtDesc();

}
