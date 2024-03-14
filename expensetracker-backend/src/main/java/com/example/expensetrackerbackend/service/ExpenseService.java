package com.example.expensetrackerbackend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.expensetrackerbackend.entity.Expense;

@Service
public class ExpenseService {

    public List<Expense> handleExpensesAndGenerateEstimates(List<Expense> expenses){
        // Define category priorities
        Map<String, Integer> categoryPriorities = new HashMap<>();
        categoryPriorities.put("education", 1);
        categoryPriorities.put("groceries", 1);
        categoryPriorities.put("health", 1);
        categoryPriorities.put("traveling", 2);
        categoryPriorities.put("clothing", 2);
        categoryPriorities.put("subscriptions", 3);
        categoryPriorities.put("takeaways", 3);
        categoryPriorities.put("other", 3);

        // Calculate modified expenses
        for (Expense expense : expenses) {
            Integer priority = categoryPriorities.get(expense.getCategory());
            if (priority != null) {
                double adjustmentPercentage = getAdjustmentPercentage(priority.intValue());
                double modifiedAmount = expense.getAmount() * (1 - adjustmentPercentage);
                expense.setAmount(modifiedAmount);
            }
        }

        return expenses;
    }

    private double getAdjustmentPercentage(int categoryPriority) {
        switch (categoryPriority) {
            case 1:
                return 0.0; // No adjustment for most essential categories
            case 2:
                return 0.05; // 5% adjustment for less essential categories
            case 3:
                return 0.10; // 10% adjustment for least essential categories
            default:
                return 0.00;
        }
    }
}