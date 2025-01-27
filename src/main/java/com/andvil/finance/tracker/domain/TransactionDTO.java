package com.andvil.finance.tracker.domain;

import java.time.LocalDate;

public class TransactionDTO {
    private Long id;
    private Double amount;
    private String comment;
    private LocalDate transaction_date;
    private Long accountId;
    private Long categoryId;
    private Long transaction_typeId;
    private Long saving_goalId;

    public TransactionDTO() {}

    public TransactionDTO(Long id, Double amount, String comment, LocalDate transaction_date, Long accountId, Long categoryId, Long transaction_typeId, Long saving_goalId) {
        this.id = id;
        this.amount = amount;
        this.comment = comment;
        this.transaction_date = transaction_date;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.transaction_typeId = transaction_typeId;
        this.saving_goalId = saving_goalId;
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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getTransaction_typeId() {
        return transaction_typeId;
    }

    public void setTransaction_typeId(Long transaction_typeId) {
        this.transaction_typeId = transaction_typeId;
    }

    public Long getSaving_goalId() {
        return saving_goalId;
    }

    public void setSaving_goalId(Long saving_goalId) {
        this.saving_goalId = saving_goalId;
    }
}
