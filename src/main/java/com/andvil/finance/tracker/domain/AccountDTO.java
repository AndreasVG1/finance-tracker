package com.andvil.finance.tracker.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class AccountDTO {

    private Long id;
    @NotEmpty(message = "Full name must be provided")
    @Size(min = 2, max = 100)
    private String full_name;
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email must be provided")
    @Size(min = 2, max = 100)
    private String email;
    @NotEmpty(message = "A password is required")
    @Size(min = 6, max = 50)
    private String password;
    @NotEmpty(message = "Currency must be provided")
    private String currency;
    private LocalDate registration_date;
    private LocalDate last_login;
    private List<Saving_GoalDTO> saving_goals;
    private List<TransactionDTO> transactions;

    public AccountDTO() {}

    public AccountDTO(Long id, String full_name, String email, String password, String currency,
                      LocalDate registration_date, LocalDate last_login) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.currency = currency;
        this.registration_date = registration_date;
        this.last_login = last_login;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public List<Saving_GoalDTO> getSaving_goals() {
        return saving_goals;
    }

    public void setSaving_goals(List<Saving_GoalDTO> saving_goals) {
        this.saving_goals = saving_goals;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
