package com.example.expensetrackerbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.expensetrackerbackend.entity.Income;
import com.example.expensetrackerbackend.repository.IncomeRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/incomes")
public class IncomeController {

    @Autowired
    private IncomeRepository incomeRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addIncome(@RequestBody Income incomeRequest) {
        String title = incomeRequest.getTitle();
        double amount = incomeRequest.getAmount();
        String description = incomeRequest.getDescription();
        Date date = (Date) incomeRequest.getDate();
        String category = incomeRequest.getCategory();

        try {
            // Validations
            if (title == null || category==null || description == null || date == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All fields are required!");
            }
            if (amount <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount must be a positive number!");
            }

            Income income = new Income(null, title, amount, null, date,category, description, null,null,null);
            incomeRepository.save(income);

            return ResponseEntity.status(HttpStatus.OK).body("Income Added");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Income>> getIncomes() {
        try {
            List<Income> incomes = incomeRepository.findAllByOrderByCreatedAtDesc();
            return ResponseEntity.status(HttpStatus.OK).body(incomes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteIncome(@PathVariable Long id) {
        try {
            incomeRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Income Deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error");
        }
    }
}