package com.andvil.finance.tracker.dal;

import com.andvil.finance.tracker.domain.Saving_Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingGoalRepository extends JpaRepository<Saving_Goal, Long> {
}
