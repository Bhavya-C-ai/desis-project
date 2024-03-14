package com.example.expensetrackerbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetrackerbackend.entity.Expense;
import com.example.expensetrackerbackend.repository.ExpenseRepository;
import com.example.expensetrackerbackend.service.ExpenseService;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/get")
    public ResponseEntity<List<Expense>> getSuggestion(){
        try{
            List<Expense> expenses = expenseRepository.findAll();
            expenses=expenseService.handleExpensesAndGenerateEstimates(expenses);
            System.out.println(expenses);
            return ResponseEntity.status(HttpStatus.OK).body(expenses);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
    }
}