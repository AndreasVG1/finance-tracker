package com.andvil.finance.tracker.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    private String comment;

    @Column(nullable = false)
    private LocalDate transaction_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_type_id", nullable = false)
    private Transaction_Type transaction_type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saving_goal_id")
    private Saving_Goal saving_goal;

    public Transaction() {}

    public Transaction(Double amount, String comment, LocalDate transaction_date,
                       Account account, Category category, Transaction_Type transaction_type, Saving_Goal saving_goal) {
        this.amount = amount;
        this.comment = comment;
        this.transaction_date = transaction_date;
        this.account = account;
        this.category = category;
        this.transaction_type = transaction_type;
        this.saving_goal = saving_goal;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Transaction_Type getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(Transaction_Type transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Saving_Goal getSaving_goal() {
        return saving_goal;
    }

    public void setSaving_goal(Saving_Goal saving_goal) {
        this.saving_goal = saving_goal;
    }
}
