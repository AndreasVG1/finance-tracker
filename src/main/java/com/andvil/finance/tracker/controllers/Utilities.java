package com.andvil.finance.tracker.controllers;

import com.andvil.finance.tracker.dal.CurrencyRepository;
import com.andvil.finance.tracker.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public class Utilities {
    public static <T> T findByIdOrThrow(Long id, JpaRepository<T, Long> repository, String entityName) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(entityName + " with ID " + id + " not found"));
    }

    public static Currency findByCodeOrThrow(String code, CurrencyRepository repository) {
        return repository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Currency" + " with code " + code + " not found"));
    }
}
