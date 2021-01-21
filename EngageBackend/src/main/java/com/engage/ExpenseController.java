package com.engage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.engage.spring.pojo.Expense;
import com.engage.spring.service.ExpenseService;
import com.engage.utilities.AuthorizationUtilities;

@Controller
@RequestMapping(path = "/app")
public class ExpenseController {

	private final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

	@Autowired
	protected JdbcTemplate jtm;

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private AuthorizationUtilities authorizationUtilities;

	@GetMapping(path = "/expense/{expenseid}")
	@CrossOrigin
	public @ResponseBody ResponseEntity<Expense> getExpense(@PathVariable String expenseid) {
		logger.info("Expense Controller :: getExpense " + expenseid);
		Integer idTmp = Integer.parseInt(expenseid.trim());
		Expense response = expenseService.getExpense(idTmp);
		if (response != null) {
			return new ResponseEntity<Expense>(response, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path = "/expenses")
	@CrossOrigin
	public @ResponseBody ResponseEntity<List<Expense>> getExpenses() {
		List<Expense> response = expenseService.getExpenses();
		if (response != null) {
			return new ResponseEntity<List<Expense>>(response, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping(path = "/expenses", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
	@CrossOrigin
	public @ResponseBody ResponseEntity<Boolean> newExpense(@RequestBody Expense engageExpense) {
		boolean result = expenseService.saveExpense(engageExpense);
		if (result) {
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(result, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/expenses/{expenseId}")
	@CrossOrigin
	public @ResponseBody ResponseEntity<String> deleteExpense(@PathVariable String expenseId) {
		logger.info("Expense Controller :: deleteexpense :: expenseId " + expenseId);
		expenseService.deleteExpense(Integer.parseInt(expenseId));
		return new ResponseEntity<>("Deleted expense " + expenseId, HttpStatus.OK);
	}

}