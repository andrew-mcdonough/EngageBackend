package com.engage.spring.service;

import java.util.List;

import com.engage.repository.model.ExpenseModel;
import com.engage.spring.pojo.Expense;

public interface ExpenseService {
	Expense getExpense(Integer expenseId);
	List<Expense> getExpenses();
	boolean saveExpense(Expense engageExpense);  
	void deleteExpense(Integer engageExpenseIdTmp);
}