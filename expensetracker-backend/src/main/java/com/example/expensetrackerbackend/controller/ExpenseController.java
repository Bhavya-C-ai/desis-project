package com.example.expensetrackerbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.expensetrackerbackend.entity.Expense;
import com.example.expensetrackerbackend.repository.ExpenseRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addExpense(@RequestBody Expense expenseRequest) {
        String title = expenseRequest.getTitle();
        double amount = expenseRequest.getAmount();
        String category = expenseRequest.getCategory();
        String description = expenseRequest.getDescription();
        Date date = expenseRequest.getDate();

        try {
            // Validations
            if (title == null || category == null || description == null || date == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All fields are required!");
            }
            if (amount <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount must be a positive number!");
            }
            Date currentDate = new Date();
            Expense result = new Expense(null, title, amount, "expense", date, category, description, null, currentDate, null);
            expenseRepository.save(result);

            return ResponseEntity.status(HttpStatus.OK).body("Expense Added");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Expense>> getExpense() {
        try {
            List<Expense> expenses = expenseRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(expenses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        try {
            expenseRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Expense Deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable Long id, @RequestBody Expense expenseRequest) {
        try {
            Expense existingExpense = expenseRepository.findById(id).orElse(null);
            if (existingExpense == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found");
            }

            // Update the fields
            existingExpense.setTitle(expenseRequest.getTitle());
            existingExpense.setAmount(expenseRequest.getAmount());
            existingExpense.setCategory(expenseRequest.getCategory());
            existingExpense.setDescription(expenseRequest.getDescription());
            existingExpense.setDate(expenseRequest.getDate());
            existingExpense.setUpdated_at(new Date());

            expenseRepository.save(existingExpense);

            return ResponseEntity.status(HttpStatus.OK).body("Expense Updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error");
        }
    }
}
