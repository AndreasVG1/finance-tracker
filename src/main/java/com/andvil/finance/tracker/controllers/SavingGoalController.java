package com.andvil.finance.tracker.controllers;

import com.andvil.finance.tracker.dal.AccountService;
import com.andvil.finance.tracker.dal.SavingGoalService;
import com.andvil.finance.tracker.domain.Account;
import com.andvil.finance.tracker.domain.Saving_Goal;
import com.andvil.finance.tracker.domain.Saving_GoalDTO;
import com.andvil.finance.tracker.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/goals")
public class SavingGoalController {

    private final SavingGoalService goalService;
    private final AccountService accountService;

    @Autowired
    public SavingGoalController(final SavingGoalService goalService, AccountService accountService) {
        this.goalService = goalService;
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Saving_GoalDTO>> getGoals() {
        List<Saving_Goal> goals = goalService.getAllGoals();

        List<Saving_GoalDTO> goalDTOs = goals.stream().map(Saving_Goal::convertToDTO).toList();
        return ResponseEntity.ok(goalDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Saving_GoalDTO> getGoalById(@PathVariable Long id) {
        Optional<Saving_Goal> goal = goalService.getGoal(id);

        if (goal.isPresent()) {
            Saving_Goal g = goal.get();
            Saving_GoalDTO goalDTO = g.convertToDTO();
            return ResponseEntity.ok(goalDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Saving_GoalDTO> createGoal(@RequestBody Saving_GoalDTO goalDTO) {
        Account account = accountService.getAccount(goalDTO.getAccountId()).orElse(null);

        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(goalDTO);
        }

        Saving_Goal goal = new Saving_Goal();
        goal.setAccount(account);
        goal.setGoal_amount(goalDTO.getGoal_amount());
        goal.setGoal_title(goalDTO.getGoal_title());
        goal.setBalance(goalDTO.getBalance());
        goal.setDue_date(goalDTO.getDue_date());
        goal.setIs_completed(goalDTO.getIs_completed());
        goal.setTransactions(new ArrayList<>());

        Saving_Goal savedGoal = goalService.createGoal(goal);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGoal.convertToDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }
}
