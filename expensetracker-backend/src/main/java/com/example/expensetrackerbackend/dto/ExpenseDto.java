package com.example.expensetrackerbackend.dto;

import java.sql.Date;

public class ExpenseDto {

    private Long id;
    private String title;
    private Double amount;
    private String type;
    private Date date;
    private String category;
    private String description;
    private String user_email;

    // Constructors, getters, and setters

    public ExpenseDto() {
    }

    public ExpenseDto(Long id, String title, Double amount, String type, Date date, String category, String description, String user_email) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.category = category;
        this.description = description;
        this.user_email = user_email;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    @Override
    public String toString() {
        return "ExpenseDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", user_email='" + user_email + '\'' +
                '}';
    }
}

