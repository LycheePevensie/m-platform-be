package com.mplatform.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mplatform.dao.ExpenseMapper;
import com.mplatform.domain.ExpenseInfo;
import com.mplatform.service.ExpenseService;

@Service("expenseService")

public class ExpenseServiceImpl implements ExpenseService {
	@Autowired
	private ExpenseMapper expenseMapper;

	@Override
	public boolean insertExpense(String expenseName, Integer expenseUser, Integer expenseManager, Double expenseValue,
			Timestamp expenseTime, String expenseTips) {
		int result = 0;
		result = expenseMapper.insertExpense(expenseName, expenseUser, expenseManager, expenseValue, expenseTime,expenseTips);
		if (result == 1)
			return true;
		else
			return false;
	}

	@Override
	public List<ExpenseInfo> selectExpense(Integer page, Integer limit) {
		int start = (page - 1) * limit;
		List<ExpenseInfo> list = expenseMapper.selectExpense(start, limit);
		for(ExpenseInfo info:list){
			Timestamp expenseTime = info.getExpenseTime();
			info.setExpenseTimeStr(expenseTime.toString().substring(0,16));
		}
		return list;
	}

	@Override
	public Integer expenseCount() {
		return expenseMapper.expenseCount();
	}
	
	@Override
	public boolean confirmExpense(Integer id) {
		int result=0;
		result = expenseMapper.confirmExpense(id);
		if(result==1) return true;
		else return false;
	}

	@Override
	public boolean rejectExpense(Integer id) {
		int result=0;
		result = expenseMapper.rejectExpense(id);
		if(result==1) return true;
		else return false;
	}

	// @Override
	// public List<LeaveInfo> searchLeave(Integer page, Integer limit, String
	// searchWay, String userCondition) {
	// int start = (page-1)*limit;
	// if(searchWay=="name"||searchWay.equals("name")){
	// System.out.println(searchWay);
	// return leaveMapper.searchUserByName(userCondition,start,limit);
	// }
	// else{
	// System.out.println("here");
	// System.out.println(prefix);
	// return leaveMapper.searchUserByDep(userCondition,start,limit);
	// }
	// }
	//
	// @Override
	// public Integer searchCount(String searchWay, String userCondition) {
	// if(searchWay=="name"){
	// return leaveMapper.countUserByName(userCondition);
	// }
	// else{
	// return leaveMapper.countUserByDep(userCondition);
	// }
	// }
}
