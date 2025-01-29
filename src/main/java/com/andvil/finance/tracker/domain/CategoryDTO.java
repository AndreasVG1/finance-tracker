package com.andvil.finance.tracker.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CategoryDTO {
    private Long id;
    @NotEmpty(message = "A category name must be provided")
    @Size(min = 2, max = 50)
    private String category_name;
    private List<TransactionDTO> transactions;

    public CategoryDTO() {}

    public CategoryDTO(Long id, String category_name) {
        this.id = id;
        this.category_name = category_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
