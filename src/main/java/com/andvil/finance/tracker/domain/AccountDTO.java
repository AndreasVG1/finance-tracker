package com.andvil.finance.tracker.domain;

import java.time.LocalDate;
import java.util.List;

public class AccountDTO {

    private Long id;
    private String full_name;
    private String email;
    private String password;
    private Long currencyId;
    private LocalDate registered_at;
    private LocalDate last_login;
    private List<Saving_GoalDTO> saving_goals;
    private List<TransactionDTO> transactions;

    public AccountDTO() {}

    public AccountDTO(Long id, String full_name, String email, String password, Long currencyId,
                      LocalDate registered_at, LocalDate last_login) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.currencyId = currencyId;
        this.registered_at = registered_at;
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

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public LocalDate getRegistered_at() {
        return registered_at;
    }

    public void setRegistered_at(LocalDate registered_at) {
        this.registered_at = registered_at;
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
