package com.andvil.finance.tracker.dal;

import com.andvil.finance.tracker.domain.Account;
import com.andvil.finance.tracker.domain.AccountDTO;
import com.andvil.finance.tracker.domain.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
        } else {
            throw new RuntimeException("Account with id " + id + " does not exist");
        }
    }

    public Account createFromDTO(AccountDTO accountDTO, Currency currency) {
        Account account = new Account();
        account.setFull_name(accountDTO.getFull_name());
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
        account.setRegistration_date(LocalDate.now());
        account.setCurrency(currency);
        account.setTransactions(new ArrayList<>());
        account.setSaving_goals(new ArrayList<>());
        return createAccount(account);
    }
}
