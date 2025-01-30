package com.andvil.finance.tracker.controllers;

import com.andvil.finance.tracker.ResourceNotFoundException;
import com.andvil.finance.tracker.dal.AccountService;
import com.andvil.finance.tracker.dal.CurrencyRepository;
import com.andvil.finance.tracker.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public ResponseEntity<List<AccountDTO>> getAllAccounts()     {
        List<Account> accounts = accountService.getAllAccounts();

        List<AccountDTO> accountDTOs = accounts.stream().map(Account::convertToDTO).toList();

        return ResponseEntity.status(HttpStatus.OK).body(accountDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccount(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account with ID " + id + " not found"));
        return ResponseEntity.status(HttpStatus.OK).body(account.convertToDTO());
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        try {
            Currency currency = Utilities.findByCodeOrThrow(accountDTO.getCurrency(), currencyRepository);

            Account account = accountService.createFromDTO(accountDTO, currency);

            return ResponseEntity.status(HttpStatus.CREATED).body(account.convertToDTO());

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(accountDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        Account account = accountService.getAccount(id).orElse(null);
        if (account != null) {
            accountService.deleteAccount(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
