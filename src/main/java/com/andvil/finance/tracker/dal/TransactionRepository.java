package com.andvil.finance.tracker.dal;

import com.andvil.finance.tracker.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @NonNull
    Page<Transaction> findAll(@NonNull Pageable pageable);
}
