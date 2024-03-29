package com.example.expensetrackerbackend.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.expensetrackerbackend.entity.Expense;
import com.example.expensetrackerbackend.entity.Income;

import com.example.expensetrackerbackend.payload.ScheduleEmailRequest;
import com.example.expensetrackerbackend.payload.ScheduleEmailResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class RoutesController {

    @Autowired
    private ExpenseController expenseController;

    @Autowired
    private IncomeController incomeController;

    @Autowired
    private SuggestionController suggestionController;

    @Autowired
    private EmailJobSchedulerController emailJobSchedulerController;

    @PostMapping("/add-expense")
    public ResponseEntity<String> addExpense(@RequestBody Expense expense) {
        return expenseController.addExpense(expense);
    }

    @GetMapping("/get-expenses")
    public ResponseEntity<List<Expense>> getExpenses() {
        return expenseController.getExpense();
    }

    @DeleteMapping("/delete-expense/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        return expenseController.deleteExpense(id);
    }

    @PutMapping("/update-expense/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        return expenseController.updateExpense(id, expense);
    }

    @PostMapping("/add-income")
    public ResponseEntity<String> addIncome(@RequestBody Income income) {
        return incomeController.addIncome(income);
    }

    @GetMapping("/get-incomes")
    public ResponseEntity<List<Income>> getIncomes() {
        return incomeController.getIncomes();
    }

    @DeleteMapping("/delete-income/{id}")
    public ResponseEntity<String> deleteIncome(@PathVariable Long id) {
        return incomeController.deleteIncome(id);
    }

    @GetMapping("/get-suggestion/{level}")
    public ResponseEntity<List<Expense>> getSuggestion(@PathVariable String level){
        System.out.println(level);
        return suggestionController.getSuggestion(level);
    }

    @GetMapping("/get-streak")
    public ResponseEntity<Integer> getStreak(@RequestParam String userEmail) {
        return expenseController.getStreak(userEmail);
    }

    @GetMapping("/get-last-added-date")
    public ResponseEntity<Date> getLastAddedDate(){
        return expenseController.getLastAddedDate();
    }

    @PostMapping("/update-streak")
    public ResponseEntity<String> updateStreak(@RequestParam String userEmail, @RequestParam int streak){
        return expenseController.updateStreak(userEmail,streak);
    }

    @PostMapping("/schedule-emails")
    public ResponseEntity<ScheduleEmailResponse> scheduleEmail(@RequestBody ScheduleEmailRequest scheduleEmailRequest){
        System.out.println("schedule Email Routes Controller");
        return emailJobSchedulerController.scheduleEmail(scheduleEmailRequest);
    }
}
