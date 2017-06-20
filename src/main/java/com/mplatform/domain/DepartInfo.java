package com.mplatform.domain;

import java.sql.Timestamp;

public class DepartInfo {
	
	private Integer departId;
	private String department;
	private String departLeader;
	private String departLeaderName;
	private Timestamp departCheckS;
	private Timestamp departCheckE;
	private Timestamp departLeaveS;
	private Timestamp departLeaveE;
	private String departCheck;
	private String departLeave;
	
	public Integer getDepartId() {
		return departId;
	}
	public void setDepartId(Integer departId) {
		this.departId = departId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDepartLeader() {
		return departLeader;
	}
	public void setDepartLeader(String departLeader) {
		this.departLeader = departLeader;
	}
	public String getDepartLeaderName() {
		return departLeaderName;
	}
	public void setDepartLeaderName(String departLeaderName) {
		this.departLeaderName = departLeaderName;
	}
	public Timestamp getDepartCheckS() {
		return departCheckS;
	}
	public void setDepartCheckS(Timestamp departCheckS) {
		this.departCheckS = departCheckS;
	}
	public Timestamp getDepartCheckE() {
		return departCheckE;
	}
	public void setDepartCheckE(Timestamp departCheckE) {
		this.departCheckE = departCheckE;
	}
	public Timestamp getDepartLeaveS() {
		return departLeaveS;
	}
	public void setDepartLeaveS(Timestamp departLeaveS) {
		this.departLeaveS = departLeaveS;
	}
	public Timestamp getDepartLeaveE() {
		return departLeaveE;
	}
	public void setDepartLeaveE(Timestamp departLeaveE) {
		this.departLeaveE = departLeaveE;
	}
	
	public String getDepartCheck() {
		return departCheck;
	}
	public void setDepartCheck(String departCheck) {
		this.departCheck = departCheck;
	}
	public String getDepartLeave() {
		return departLeave;
	}
	public void setDepartLeave(String departLeave) {
		this.departLeave = departLeave;
	}
	@Override
	public String toString() {
		return "DepartInfo [departId=" + departId + ", department=" + department + ", departLeader=" + departLeader
				+ ", departLeaderName=" + departLeaderName + ", departCheckS=" + departCheckS + ", departCheckE="
				+ departCheckE + ", departLeaveS=" + departLeaveS + ", departLeaveE=" + departLeaveE + ", departCheck="
				+ departCheck + ", departLeave=" + departLeave + "]";
	}
	
}
