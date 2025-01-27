package com.andvil.finance.tracker.controllers;

import com.andvil.finance.tracker.dal.AccountService;
import com.andvil.finance.tracker.dal.CurrencyRepository;
import com.andvil.finance.tracker.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public AccountController(AccountService accountService, CurrencyRepository currencyRepository) {
        this.accountService = accountService;
        this.currencyRepository = currencyRepository;
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();

        List<AccountDTO> accountDTOs = accounts.stream().map(Account::convertToDTO).toList();

        return ResponseEntity.ok(accountDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        Optional<Account> account = accountService.getAccount(id);

        if (account.isPresent()) {
            Account a = account.get();
            AccountDTO accountDTO = a.convertToDTO();
            return ResponseEntity.ok(accountDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        Currency currency = currencyRepository.findByCode(accountDTO.getCurrency()).orElse(null);
        if (currency == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(accountDTO);
        }
        Account account = new Account();
        account.setFull_name(accountDTO.getFull_name());
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
        account.setRegistration_date(LocalDate.now());
        account.setCurrency(currency);
        account.setTransactions(new ArrayList<Transaction>());
        account.setSaving_goals(new ArrayList<Saving_Goal>());

        Account savedAccount = accountService.createAccount(account);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount.convertToDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
