package com.andvil.finance.tracker.domain;


import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

public class TransactionDTO {
    private Long id;
    @NonNull
    private Double amount;
    @Size(max = 250)
    private String comment;
    private LocalDate transactionDate;
    @NonNull
    private Long accountId;
    @NonNull
    private Long categoryId;
    @NonNull
    private Long transactionTypeId;
    private Long savingGoalId;

    public TransactionDTO() {
        amount = 0.0;
        accountId = 0L;
        categoryId = 0L;
        transactionTypeId = 0L;
    }

    public TransactionDTO(Long id, @NonNull Double amount, String comment, LocalDate transactionDate,
                          @NonNull Long accountId, @NonNull Long categoryId,
                          @NonNull Long transactionTypeId, Long savingGoalId) {
        this.id = id;
        this.amount = amount;
        this.comment = comment;
        this.transactionDate = transactionDate;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.transactionTypeId = transactionTypeId;
        this.savingGoalId = savingGoalId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public Double getAmount() {
        return amount;
    }

    public void setAmount(@NonNull Double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @NonNull
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(@NonNull Long accountId) {
        this.accountId = accountId;
    }

    @NonNull
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NonNull Long categoryId) {
        this.categoryId = categoryId;
    }

    @NonNull
    public Long getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(@NonNull Long transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public Long getSavingGoalId() {
        return savingGoalId;
    }

    public void setSavingGoalId(Long savingGoalId) {
        this.savingGoalId = savingGoalId;
    }
}
