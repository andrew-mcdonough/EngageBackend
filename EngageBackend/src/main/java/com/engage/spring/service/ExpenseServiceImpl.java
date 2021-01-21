package com.engage.spring.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.engage.mapping.ExpenseMapper;
import com.engage.repository.ExpenseRepository;
import com.engage.repository.model.ExpenseModel;
import com.engage.spring.pojo.Expense;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	private final Logger logger = LoggerFactory.getLogger(ExpenseServiceImpl.class);

	@Autowired
	JdbcTemplate jdbc;

	@Autowired
	NamedParameterJdbcTemplate namedJdbc;

	private final ExpenseMapper expenseMapper;
	private final ExpenseRepository expenseRepository;

	@Autowired
	public ExpenseServiceImpl(ExpenseMapper expenseMapper, ExpenseRepository expenseRepository) {
		this.expenseMapper = expenseMapper;
		this.expenseRepository = expenseRepository;
	}

	@Transactional
	public ExpenseModel constructStoragePathAndSave(ExpenseModel beaconDocDB, Integer tradeFairId,
			String tradeFairFolderName, String exhibitorFolderName) {
		final String updateSql = "UPDATE beacon_document SET storagePath = ? WHERE id = ?";

		ExpenseModel document = expenseRepository.save(beaconDocDB);

		// Object[] params = { beaconDocDB.getStoragePath(), document.getId() };
		int[] types = { Types.VARCHAR, Types.INTEGER };
		logger.info("updateSql " + updateSql);
		Object[] params = null;
		int rows = jdbc.update(updateSql, params, types);
		logger.info(rows + " row(s) updated.");

		return document;
	}

	@Override
	public Expense getExpense(Integer expenseId) {
		logger.info("Expense Service getExpense " + expenseId);
		Expense expenses = null;
		ExpenseModel expensesDB = expenseRepository.findById(expenseId).orElse(null);
		return expensesDB != null ? expenseMapper.mapExpenseDB(expensesDB) : null;
	}

	@Override
	public List<Expense> getExpenses() {
		  List<ExpenseModel> expenses = new ArrayList<ExpenseModel>();
		  List<Expense> expensesResult = new ArrayList<Expense>();
		  Expense expense = null;
		  expenseRepository.findAll().forEach(expenses::add);
		  for(ExpenseModel expenseDB : expenses)
		  {
			  expense = expenseMapper.mapExpenseDB(expenseDB); 
			  expensesResult.add(expense);
		  }
		  return expensesResult;
	}

	@Override
	public boolean saveExpense(Expense engageExpense) {
		logger.info("Expense Service saveExpense " + engageExpense.getReason());
		ExpenseModel expensesDB = new ExpenseModel();
		expensesDB = expenseMapper.mapExpensePojo(engageExpense);
		return expenseRepository.save(expensesDB) != null ? true : false;
	}

	@Override
	public void deleteExpense(Integer engageExpenseIdTmp) {
		expenseRepository.deleteById(engageExpenseIdTmp);
	}

}