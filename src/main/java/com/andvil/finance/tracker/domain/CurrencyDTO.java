package com.andvil.finance.tracker.domain;

import java.util.List;

public class CurrencyDTO {
    private Long id;
    private String currency_name;
    private String currency_code;
    private String currency_symbol;
    private List<AccountDTO> accounts;

    public CurrencyDTO() {}

    public CurrencyDTO(Long id, String currency_name, String currency_code, String currency_symbol, List<AccountDTO> accounts) {
        this.id = id;
        this.currency_name = currency_name;
        this.currency_code = currency_code;
        this.currency_symbol = currency_symbol;
        this.accounts = accounts;
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

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }
}
