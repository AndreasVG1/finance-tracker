package com.andvil.finance.tracker.controllers;

import com.andvil.finance.tracker.ResourceNotFoundException;
import com.andvil.finance.tracker.dal.AccountRepository;
import com.andvil.finance.tracker.dal.SavingGoalService;
import com.andvil.finance.tracker.domain.Account;
import com.andvil.finance.tracker.domain.Saving_Goal;
import com.andvil.finance.tracker.domain.Saving_GoalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class SavingGoalController {

    private final SavingGoalService goalService;
    private final AccountRepository accountRepository;

    @Autowired
    public SavingGoalController(final SavingGoalService goalService, AccountRepository accountRepository) {
        this.goalService = goalService;
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public ResponseEntity<List<Saving_GoalDTO>> getGoals() {
        List<Saving_Goal> goals = goalService.getAllGoals();

        List<Saving_GoalDTO> goalDTOs = goals.stream().map(Saving_Goal::convertToDTO).toList();
        return ResponseEntity.ok(goalDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Saving_GoalDTO> getGoalById(@PathVariable Long id) {
        Saving_Goal goal = goalService.getGoal(id)
                .orElseThrow(() -> new ResourceNotFoundException("Saving Goal with ID " + id + " not found"));
        return ResponseEntity.ok(goal.convertToDTO());
    }

    @PostMapping
    public ResponseEntity<Saving_GoalDTO> createGoal(@RequestBody Saving_GoalDTO goalDTO) {
        try {
            Account account = Utilities.findByIdOrThrow(goalDTO.getAccountId(), accountRepository, "Account");

            Saving_Goal goal = goalService.createFromDTO(account, goalDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(goal.convertToDTO());

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(goalDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        Saving_Goal goal = goalService.getGoal(id).orElse(null);
        if (goal != null) {
            goalService.deleteGoal(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
