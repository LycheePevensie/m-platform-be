package com.mplatform.domain;

import java.sql.Timestamp;

public class CheckInfo {
	private Integer checkId;
	private Integer checkUser;
//	private Integer checkWay;
	/**
	 * 签到状态
	 */
	private Integer checkStatus;
	private String checkImg;
	private Timestamp checkTime;
    private String checkPlace;
	private String outReason;
	private String otherReason;
//	private String checkDate;
//	private String checkWeek;
//	/**
//	 * 签退状态
//	 */
//	private Integer leaveStatus;

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public Integer getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(Integer checkUser) {
		this.checkUser = checkUser;
	}

//	public Integer getCheckWay() {
//		return checkWay;
//	}
//
//	public void setCheckWay(Integer checkWay) {
//		this.checkWay = checkWay;
//	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getCheckImg() {
		return checkImg;
	}

	public void setCheckImg(String checkImg) {
		this.checkImg = checkImg;
	}

	public Timestamp getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Timestamp checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckPlace() {
		return checkPlace;
	}

	public void setCheckPlace(String checkPlace) {
		this.checkPlace = checkPlace;
	}

	public String getOutReason() {
		return outReason;
	}

	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}

	public String getOtherReason() {
		return otherReason;
	}

	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}

	@Override
	public String toString() {
		return "CheckInfo [checkId=" + checkId + ", checkUser=" + checkUser + ", checkStatus=" + checkStatus
				+ ", checkImg=" + checkImg + ", checkTime=" + checkTime + ", checkPlace=" + checkPlace + ", outReason="
				+ outReason + ", otherReason=" + otherReason + "]";
	}

//	public String getCheckDate() {
//		return checkDate;
//	}
//
//	public void setCheckDate(String checkDate) {
//		this.checkDate = checkDate;
//	}
//
//	public String getCheckWeek() {
//		return checkWeek;
//	}
//
//	public void setCheckWeek(String checkWeek) {
//		this.checkWeek = checkWeek;
//	}

//	public Integer getLeaveStatus() {
//		return leaveStatus;
//	}
//
//	public void setLeaveStatus(Integer leaveStatus) {
//		this.leaveStatus = leaveStatus;
//	}

}
