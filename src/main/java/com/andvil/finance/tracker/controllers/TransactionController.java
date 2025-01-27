package com.andvil.finance.tracker.controllers;

import com.andvil.finance.tracker.dal.*;
import com.andvil.finance.tracker.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final SavingGoalRepository savingGoalRepository;

    @Autowired
    public TransactionController(final TransactionService transactionService, AccountRepository accountRepository, CategoryRepository categoryRepository, TransactionTypeRepository transactionTypeRepository, SavingGoalRepository savingGoalRepository) {
        this.transactionService = transactionService;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.transactionTypeRepository = transactionTypeRepository;
        this.savingGoalRepository = savingGoalRepository;
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();

        List<TransactionDTO> transactionDTOs = transactions.stream().map(Transaction::convertToDTO).toList();

        return ResponseEntity.ok(transactionDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.getTransaction(id);

        if (transaction.isPresent()) {
            Transaction t = transaction.get();
            TransactionDTO transactionDTO = t.convertToDTO();
            return ResponseEntity.ok(transactionDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        Account account = accountRepository.findById(transactionDTO.getAccountId()).orElse(null);
        Transaction_Type type = transactionTypeRepository.findById(transactionDTO.getTransaction_typeId()).orElse(null);
        Category category = categoryRepository.findById(transactionDTO.getCategoryId()).orElse(null);
        Saving_Goal goal = null;

        if (transactionDTO.getSaving_goalId() != null) {
            goal = savingGoalRepository.findById(transactionDTO.getSaving_goalId()).orElse(null);
        }

        if (account == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(transactionDTO);
        }
        if (type == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(transactionDTO);
        }
        if (category == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(transactionDTO);
        }
        if (transactionDTO.getSaving_goalId() != null && goal == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(transactionDTO);
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setCategory(category);
        transaction.setAccount(account);
        transaction.setTransaction_type(type);
        transaction.setSaving_goal(goal);
        transaction.setComment(transactionDTO.getComment());
        transaction.setTransaction_date(transactionDTO.getTransaction_date() != null ? transactionDTO.getTransaction_date() : LocalDate.now());

        if (goal != null) {
            goal.setBalance(goal.getBalance() + transaction.getAmount());
        }

        Transaction savedTransaction = transactionService.createTransaction(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction.convertToDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
