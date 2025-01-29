package com.andvil.finance.tracker.dal;

import com.andvil.finance.tracker.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
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

    public Transaction createFromDTO(TransactionDTO transactionDTO, Account account, Category category, Saving_Goal goal, Transaction_Type type) {
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
        return createTransaction(transaction);
    }
}
