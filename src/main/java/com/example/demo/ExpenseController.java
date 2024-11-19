import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Get all expenses
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Add a new expense
    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }

    // Update an expense
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        expense.setDescription(expenseDetails.getDescription());
        expense.setAmount(expenseDetails.getAmount());
        expense.setCategory(expenseDetails.getCategory());
        return expenseRepository.save(expense);
    }

    // Delete an expense
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
    }
}
