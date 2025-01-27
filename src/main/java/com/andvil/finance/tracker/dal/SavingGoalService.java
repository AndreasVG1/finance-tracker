package com.andvil.finance.tracker.dal;

import com.andvil.finance.tracker.domain.Saving_Goal;
import com.andvil.finance.tracker.domain.Saving_GoalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavingGoalService {

    private final SavingGoalRepository goalRepository;

    @Autowired
    public SavingGoalService(SavingGoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public List<Saving_Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    public Optional<Saving_Goal> getGoal(Long id) {
        return goalRepository.findById(id);
    }

    public Saving_Goal createGoal(Saving_Goal goal) {
        return goalRepository.save(goal);
    }

    public void deleteGoal(Long id) {
        if (goalRepository.existsById(id)) {
            goalRepository.deleteById(id);
        } else {
            throw new RuntimeException("Goal not found with id: " + id);
        }
    }

    public Saving_Goal updateGoal(Saving_GoalDTO updatedGoal, Saving_Goal goal) {
        goal.setGoal_title(updatedGoal.getGoal_title());
        goal.setGoal_amount(updatedGoal.getGoal_amount());
        goal.setBalance(updatedGoal.getBalance());
        goal.setDue_date(updatedGoal.getDue_date());
        goal.setIs_completed(updatedGoal.getIs_completed());
        goal.setCompleted_date(updatedGoal.getCompleted_date());
        goal.setTransactions(goal.getTransactions());
        return goalRepository.save(goal);
    }

    public Saving_Goal partialUpdateGoal(Saving_GoalDTO updatedGoal, Saving_Goal goal) {
        if (updatedGoal.getGoal_title() != null) {
            goal.setGoal_title(updatedGoal.getGoal_title());
        }
        if (updatedGoal.getGoal_amount() != null) {
            goal.setGoal_amount(updatedGoal.getGoal_amount());
        }
        if (updatedGoal.getBalance() != null) {
            goal.setBalance(updatedGoal.getBalance());
        }
        if (updatedGoal.getDue_date() != null) {
            goal.setDue_date(updatedGoal.getDue_date());
        }
        if (updatedGoal.getIs_completed() != null) {
            goal.setIs_completed(updatedGoal.getIs_completed());
        }
        if (updatedGoal.getCompleted_date() != null) {
            goal.setCompleted_date(updatedGoal.getCompleted_date());
        }
        goal.setTransactions(goal.getTransactions());
        return goalRepository.save(goal);
    }
}
