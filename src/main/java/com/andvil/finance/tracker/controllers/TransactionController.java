package com.andvil.finance.tracker.controllers;

import com.andvil.finance.tracker.ResourceNotFoundException;
import com.andvil.finance.tracker.dal.*;
import com.andvil.finance.tracker.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<TransactionDTO>> getTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "transactionDate,desc") String[] sort) {

        Sort sortConfig = Sort.by(Sort.Order.desc("transactionDate"));

        Pageable pageable = PageRequest.of(page, size, sortConfig);

        Page<Transaction> transactionPage = transactionService.getAllTransactions(pageable);

        Page<TransactionDTO> transactionDTOs = transactionPage.map(Transaction::convertToDTO);

        return ResponseEntity.status(HttpStatus.OK).body(transactionDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransaction(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction with ID " + id + " not found"));
        return ResponseEntity.status(HttpStatus.OK).body(transaction.convertToDTO());
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            Account account = Utilities.findByIdOrThrow(transactionDTO.getAccountId(), accountRepository, "Account");
            Transaction_Type type = Utilities.findByIdOrThrow(transactionDTO.getTransactionTypeId(), transactionTypeRepository, "Transaction Type");
            Category category = Utilities.findByIdOrThrow(transactionDTO.getCategoryId(), categoryRepository, "Category");
            Saving_Goal goal = transactionDTO.getSavingGoalId() != null
                    ? Utilities.findByIdOrThrow(transactionDTO.getSavingGoalId(), savingGoalRepository, "Saving Goal")
                    : null;

            Transaction transaction = transactionService.createFromDTO(transactionDTO, account, category, goal, type);

            return ResponseEntity.status(HttpStatus.CREATED).body(transaction.convertToDTO());

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(transactionDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransaction(id).orElse(null);
        if (transaction != null) {
            transactionService.deleteTransaction(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
