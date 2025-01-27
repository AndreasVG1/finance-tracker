package com.andvil.finance.tracker.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String currency_name;

    @Column(nullable = false, length = 5)
    private String currency_code;

    @Column(nullable = false, length = 1)
    private String currency_symbol;

    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accounts;

    public Currency() {}

    public Currency(String currency_name, String currency_code, String currency_symbol) {
        this.currency_name = currency_name;
        this.currency_code = currency_code;
        this.currency_symbol = currency_symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getCurrency_symbol() {
        return currency_symbol;
    }

    public void setCurrency_symbol(String currency_symbol) {
        this.currency_symbol = currency_symbol;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
