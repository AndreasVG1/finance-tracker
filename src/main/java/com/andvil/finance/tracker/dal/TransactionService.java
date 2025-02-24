package com.andvil.finance.tracker.dal;

import com.andvil.finance.tracker.ResourceNotFoundException;
import com.andvil.finance.tracker.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final CategoryRepository categoryRepository;
    private final SavingGoalRepository savingGoalRepository;



    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, TransactionTypeRepository transactionTypeRepository, CategoryRepository categoryRepository, SavingGoalRepository savingGoalRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.transactionTypeRepository = transactionTypeRepository;
        this.categoryRepository = categoryRepository;
        this.savingGoalRepository = savingGoalRepository;
    }

    public Page<Transaction> getAllTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    public Optional<Transaction> getTransaction(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Transaction not found with id: " + id);
        }
    }

    public Transaction updateTransaction(Transaction transaction, TransactionDTO updatedTransaction) {
        transaction.setAmount(updatedTransaction.getAmount());
        transaction.setComment(updatedTransaction.getComment());
        transaction.setTransactionDate(updatedTransaction.getTransactionDate());

        if (!Objects.equals(updatedTransaction.getAccountId(), transaction.getAccount().getId())) {
            Account newAccount = accountRepository.findById(updatedTransaction.getAccountId())
                    .orElseThrow(() -> new ResourceNotFoundException("Transaction with ID " + updatedTransaction.getAccountId() + " not found"));
            transaction.setAccount(newAccount);
        }

        if (!Objects.equals(updatedTransaction.getCategoryId(), transaction.getCategory().getId())) {
            Category newCategory = categoryRepository.findById(updatedTransaction.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Transaction with ID " + updatedTransaction.getCategoryId() + " not found"));
            transaction.setCategory(newCategory);
        }

        if (!Objects.equals(updatedTransaction.getTransactionTypeId(), transaction.getTransaction_type().getId())) {
            Transaction_Type newType = transactionTypeRepository.findById(updatedTransaction.getTransactionTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Transaction with ID " + updatedTransaction.getTransactionTypeId() + " not found"));
            transaction.setTransaction_type(newType);
        }

        if (!Objects.equals(updatedTransaction.getSavingGoalId(), transaction.getSaving_goal().getId())) {
            Saving_Goal newGoal = savingGoalRepository.findById(updatedTransaction.getSavingGoalId())
                    .orElseThrow(() -> new ResourceNotFoundException("Transaction with ID " + updatedTransaction.getSavingGoalId() + " not found"));
            transaction.setSaving_goal(newGoal);
        }

        return transactionRepository.save(transaction);
    }

    public Transaction createFromDTO(TransactionDTO transactionDTO, Account account, Category category, Saving_Goal goal, Transaction_Type type) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setCategory(category);
        transaction.setAccount(account);
        transaction.setTransaction_type(type);
        transaction.setSaving_goal(goal);
        transaction.setComment(transactionDTO.getComment());
        transaction.setTransactionDate(transactionDTO.getTransactionDate() != null ? transactionDTO.getTransactionDate() : LocalDate.now());

        if (goal != null) {
            goal.setBalance(goal.getBalance() + transaction.getAmount());
        }
        return createTransaction(transaction);
    }
}
