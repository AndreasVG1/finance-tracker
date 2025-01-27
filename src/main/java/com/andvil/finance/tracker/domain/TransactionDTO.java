package com.andvil.finance.tracker.domain;

import java.time.LocalDate;

public class TransactionDTO {
    private Long id;
    private Double amount;
    private String comment;
    private LocalDate transaction_date;
    private AccountDTO account;
    private CategoryDTO category;
    private Transaction_Type transaction_type;
    private Saving_GoalDTO saving_goal;

    public TransactionDTO() {}

    public TransactionDTO(Long id, Double amount, String comment, LocalDate transaction_date, Transaction_Type transaction_type) {
        this.id = id;
        this.amount = amount;
        this.comment = comment;
        this.transaction_date = transaction_date;
        this.transaction_type = transaction_type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(LocalDate transaction_date) {
        this.transaction_date = transaction_date;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public Transaction_Type getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(Transaction_Type transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Saving_GoalDTO getSaving_goal() {
        return saving_goal;
    }

    public void setSaving_goal(Saving_GoalDTO saving_goal) {
        this.saving_goal = saving_goal;
    }
}
