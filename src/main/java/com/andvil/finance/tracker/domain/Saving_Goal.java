package com.andvil.finance.tracker.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Saving_Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false)
    private String goal_title;

    @Column(nullable = false)
    private Double goal_amount;

    @Column(nullable = false)
    private Double balance;

    private LocalDate due_date;

    @Column(nullable = false)
    private Boolean is_completed;

    private LocalDate completed_date;

    @OneToMany(mappedBy = "saving_goal", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    public Saving_Goal() {}

    public Saving_Goal(Account account, String goal_title, Double goal_amount,
                       Double balance, LocalDate due_date, Boolean is_completed, LocalDate completed_date) {
        this.account = account;
        this.goal_title = goal_title;
        this.goal_amount = goal_amount;
        this.balance = balance;
        this.due_date = due_date;
        this.is_completed = is_completed;
        this.completed_date = completed_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getGoal_title() {
        return goal_title;
    }

    public void setGoal_title(String goal_title) {
        this.goal_title = goal_title;
    }

    public Double getGoal_amount() {
        return goal_amount;
    }

    public void setGoal_amount(Double goal_amount) {
        this.goal_amount = goal_amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public Boolean getIs_completed() {
        return is_completed;
    }

    public void setIs_completed(Boolean is_completed) {
        this.is_completed = is_completed;
    }

    public LocalDate getCompleted_date() {
        return completed_date;
    }

    public void setCompleted_date(LocalDate completed_date) {
        this.completed_date = completed_date;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
