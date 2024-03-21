package com.example.expensetrackerbackend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.expensetrackerbackend.entity.Expense;

@Service
public class ExpenseService {

    public List<Expense> handleExpensesAndGenerateEstimates(List<Expense> expenses, String level){
        // Define category priorities
        Map<String, Integer> categoryPriorities = new HashMap<>();
        categoryPriorities.put("education", 1);
        categoryPriorities.put("groceries", 1);
        categoryPriorities.put("health", 1);
        categoryPriorities.put("travelling", 2);
        categoryPriorities.put("clothing", 2);
        categoryPriorities.put("subscriptions", 3);
        categoryPriorities.put("takeaways", 3);
        categoryPriorities.put("other", 3);

        int lvl;
        
        switch (level) {
            case "little":
                lvl = 1;
                break;
            case "moderate":
                lvl = 2;
                break;
            case "lot":
                lvl = 3;
                break;
            default:
                lvl = 2; // Default to moderate saving
                break;
        }

        // Calculate modified expenses
        for (Expense expense : expenses) {
            Integer priority = categoryPriorities.get(expense.getCategory());
            if (priority != null) {
                double adjustmentPercentage = getAdjustmentPercentage(priority.intValue(),lvl);
                double modifiedAmount = expense.getAmount() * (1 - adjustmentPercentage);
                expense.setAmount(modifiedAmount);
            }
        }

        return expenses;
    }

    private double getAdjustmentPercentage(int categoryPriority, int lvl) {
        switch (categoryPriority) {
            case 1:
                return 0.0; // No adjustment for most essential categories
            case 2:
                return lvl*0.05; // lvl*5% adjustment for less essential categories
            case 3:
                return lvl*0.10; // lvl*10% adjustment for least essential categories
            default:
                return 0.00;
        }
    }
}
