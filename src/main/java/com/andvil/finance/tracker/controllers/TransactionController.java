package com.andvil.finance.tracker.controllers;

import com.andvil.finance.tracker.ResourceNotFoundException;
import com.andvil.finance.tracker.dal.*;
import com.andvil.finance.tracker.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
        Transaction transaction = transactionService.getTransaction(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction with ID " + id + " not found"));
        return ResponseEntity.ok(transaction.convertToDTO());
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            Account account = Utilities.findByIdOrThrow(transactionDTO.getAccountId(), accountRepository, "Account");
            Transaction_Type type = Utilities.findByIdOrThrow(transactionDTO.getTransaction_typeId(), transactionTypeRepository, "Transaction Type");
            Category category = Utilities.findByIdOrThrow(transactionDTO.getCategoryId(), categoryRepository, "Category");
            Saving_Goal goal = transactionDTO.getSaving_goalId() != null
                    ? Utilities.findByIdOrThrow(transactionDTO.getSaving_goalId(), savingGoalRepository, "Saving Goal")
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
