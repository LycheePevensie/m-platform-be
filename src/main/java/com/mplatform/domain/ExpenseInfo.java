package com.mplatform.domain;

import java.sql.Timestamp;

public class ExpenseInfo {
	private Integer expenseId;
	private String expenseName;
	private Integer expenseUser;
	private Integer expenseManager;
	private String userName;
	private String managerName;
	private Double expenseValue;
	private Timestamp expenseTime;
	private String expenseTimeStr;
	private String expenseStatus;
	private String expenseTips;

	public Integer getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Integer expenseId) {
		this.expenseId = expenseId;
	}

	public String getExpenseName() {
		return expenseName;
	}

	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	public Integer getExpenseUser() {
		return expenseUser;
	}

	public void setExpenseUser(Integer expenseUser) {
		this.expenseUser = expenseUser;
	}

	public Integer getExpenseManager() {
		return expenseManager;
	}

	public void setExpenseManager(Integer expenseManager) {
		this.expenseManager = expenseManager;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Double getExpenseValue() {
		return expenseValue;
	}

	public void setExpenseValue(Double expenseValue) {
		this.expenseValue = expenseValue;
	}

	public Timestamp getExpenseTime() {
		return expenseTime;
	}

	public void setExpenseTime(Timestamp expenseTime) {
		this.expenseTime = expenseTime;
	}

	public String getExpenseTimeStr() {
		return expenseTimeStr;
	}

	public void setExpenseTimeStr(String expenseTimeStr) {
		this.expenseTimeStr = expenseTimeStr;
	}

	public String getExpenseStatus() {
		return expenseStatus;
	}

	public void setExpenseStatus(String expenseStatus) {
		this.expenseStatus = expenseStatus;
	}

	public String getExpenseTips() {
		return expenseTips;
	}

	public void setExpenseTips(String expenseTips) {
		this.expenseTips = expenseTips;
	}

	@Override
	public String toString() {
		return "ExpenseInfo [expenseId=" + expenseId + ", expenseName=" + expenseName + ", expenseUser=" + expenseUser
				+ ", expenseManager=" + expenseManager + ", userName=" + userName + ", managerName=" + managerName
				+ ", expenseValue=" + expenseValue + ", expenseTime=" + expenseTime + ", expenseTimeStr="
				+ expenseTimeStr + ", expenseStatus=" + expenseStatus + ", expenseTips=" + expenseTips + "]";
	}



}
