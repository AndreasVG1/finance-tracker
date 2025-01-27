package com.andvil.finance.tracker.dal;

import com.andvil.finance.tracker.domain.Transaction_Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<Transaction_Type, Long> {
}
