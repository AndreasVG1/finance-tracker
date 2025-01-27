package com.andvil.finance.tracker.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String full_name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate registration_date;

    private LocalDate last_login;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Saving_Goal> saving_goals;

    public Account() {}

    public Account(String full_name, String email, String password,
                   LocalDate registration_date, LocalDate last_login, Currency currency) {
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.registration_date = registration_date;
        this.last_login = last_login;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(LocalDate registration_date) {
        this.registration_date = registration_date;
    }

    public LocalDate getLast_login() {
        return last_login;
    }

    public void setLast_login(LocalDate last_login) {
        this.last_login = last_login;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Saving_Goal> getSaving_goals() {
        return saving_goals;
    }

    public void setSaving_goals(List<Saving_Goal> saving_goals) {
        this.saving_goals = saving_goals;
    }

    public AccountDTO convertToDTO() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(id);
        accountDTO.setFull_name(full_name);
        accountDTO.setEmail(email);
        accountDTO.setPassword(password);
        accountDTO.setRegistration_date(registration_date);
        accountDTO.setLast_login(last_login);
        accountDTO.setCurrency(currency.getCode());
        accountDTO.setTransactions(transactions.stream().map(Transaction::convertToDTO).toList());
        accountDTO.setSaving_goals(saving_goals.stream().map(Saving_Goal::convertToDTO).toList());
        return accountDTO;
    }
}
