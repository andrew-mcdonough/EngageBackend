package com.engage.mapping;

import com.engage.repository.model.ExpenseModel;
import com.engage.spring.pojo.Expense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ExpenseMapper {
	Logger logger = LoggerFactory.getLogger(ExpenseMapper.class);

	public ExpenseMapper() {

	}

	public Expense mapExpenseDB(ExpenseModel expenseDB) {
		logger.info("Expense mapper mapExpenseDB date <" + expenseDB.getExpenseDate() + ">");
		Expense expense = new Expense();
		expense.setAmount(expenseDB.getAmount());
		expense.setVat(expenseDB.getVat());
		if (expenseDB.getExpenseDate() != null) {
			String utilDate = expenseDB.getExpenseDate().toString();
			expense.setDate(utilDate);
		}
		expense.setReason(expenseDB.getReason());
		return expense;
	}

	public ExpenseModel mapExpensePojo(Expense expensePojo) {
		logger.info("Expense mapper mapExpensePojo" + expensePojo.getDate());
		ExpenseModel expenseDB = new ExpenseModel();
		expenseDB.setAmount(expensePojo.getAmount());
		expenseDB.setVat(expensePojo.getVat());
		if (expensePojo.getDate() != null) {
			try {
				Date dateTmp = new SimpleDateFormat("dd/MM/yyyy").parse(expensePojo.getDate());
				java.sql.Date sqlDate = new java.sql.Date(dateTmp.getTime());
				expenseDB.setExpenseDate(sqlDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		expenseDB.setReason(expensePojo.getReason());
		return expenseDB;
	}

	public List<Expense> mapBeaconQRDocument(Expense beaconQR) {
		List<Expense> expenses = new ArrayList<>();

		return expenses;
	}
}
