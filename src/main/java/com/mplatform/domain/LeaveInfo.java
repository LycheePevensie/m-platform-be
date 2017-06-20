package com.mplatform.domain;

import java.sql.Timestamp;

public class LeaveInfo {
	private Integer leaveId;
	private Integer leaveUser;
	private Integer leaveManager;
	private String userName;
	private String managerName;
	private String leaveTime;
	private Timestamp startTime;
	private Timestamp endTime;
	private String leaveType;
	private String leaveReason;
	private String leaveTips;
	private Integer leaveStatus;
	public Integer getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}
	public Integer getLeaveUser() {
		return leaveUser;
	}
	public void setLeaveUser(Integer leaveUser) {
		this.leaveUser = leaveUser;
	}
	public Integer getLeaveManager() {
		return leaveManager;
	}
	public void setLeaveManager(Integer leaveManager) {
		this.leaveManager = leaveManager;
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
	public String getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getLeaveReason() {
		return leaveReason;
	}
	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}
	public String getLeaveTips() {
		return leaveTips;
	}
	public void setLeaveTips(String leaveTips) {
		this.leaveTips = leaveTips;
	}
	public Integer getLeaveStatus() {
		return leaveStatus;
	}
	public void setLeaveStatus(Integer leaveStatus) {
		this.leaveStatus = leaveStatus;
	}
	@Override
	public String toString() {
		return "LeaveInfo [leaveId=" + leaveId + ", leaveUser=" + leaveUser + ", leaveManager=" + leaveManager
				+ ", userName=" + userName + ", managerName=" + managerName + ", leaveTime=" + leaveTime
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", leaveType=" + leaveType + ", leaveReason="
				+ leaveReason + ", leaveTips=" + leaveTips + ", leaveStatus=" + leaveStatus + "]";
	}
}
