package com.example.expensetrackerbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.expensetrackerbackend.entity.Expense;
import com.example.expensetrackerbackend.repository.ExpenseRepository;
import com.example.expensetrackerbackend.entity.User;
import com.example.expensetrackerbackend.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;
    
    @Autowired
    private UserRepository userRepository;

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


    @GetMapping("/get-streak")
    public ResponseEntity<Integer> getStreak(@RequestParam String userEmail) {
        try {
            User user = userRepository.findByEmail(userEmail);
            if (user != null) {
                int streak = user.getStreak();
                return ResponseEntity.status(HttpStatus.OK).body(streak);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // User not found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/get-last-added-date")
    public ResponseEntity<Date> getLastAddedDate() {
        try {
            Optional<Date> lastAddedDateOptional = expenseRepository.findLastAddedDate();
            if (lastAddedDateOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(lastAddedDateOptional.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // No last added date found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/update-streak")
    public ResponseEntity<String> updateStreak(@RequestParam String userEmail, @RequestParam int streak) {
        try {
            User user = userRepository.findByEmail(userEmail);
            if (user != null) {
                user.setStreak(streak);
                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("Streak updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error");
        }
    }
}
