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

    @Column(name = "currency_code", nullable = false, length = 5)
    private String code;

    @Column(name = "currency_symbol", nullable = false, length = 1)
    private String symbol;

    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accounts;

    public Currency() {}

    public Currency(String currency_name, String code, String symbol) {
        this.currency_name = currency_name;
        this.code = code;
        this.symbol = symbol;
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

    public String getCode() {
        return code;
    }

    public void setCode(String currency_code) {
        this.code = currency_code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String currency_symbol) {
        this.symbol = currency_symbol;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public CurrencyDTO convertToDTO() {
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setId(id);
        currencyDTO.setCurrency_name(currency_name);
        currencyDTO.setCurrency_code(code);
        currencyDTO.setCurrency_symbol(symbol);
        currencyDTO.setAccounts(accounts.stream().map(Account::convertToDTO).toList());
        return currencyDTO;
    }
}
