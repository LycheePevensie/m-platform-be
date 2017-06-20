package com.mplatform.service;

import java.sql.Timestamp;
import java.util.List;

import com.mplatform.domain.ExpenseInfo;

public interface ExpenseService {
	boolean insertExpense(String expenseName, Integer expenseUser, Integer expenseManager, Double expenseValue,
			Timestamp expenseTime, String expenseTips);

	Integer expenseCount();

	List<ExpenseInfo> selectExpense(Integer page, Integer limit);

	boolean confirmExpense(Integer id);

	boolean rejectExpense(Integer id);
}