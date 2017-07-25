package com.mplatform.domain;

import java.sql.Timestamp;

public class UserInfo {
	private Integer userId;
	private String userPwd;
	private String userName;
	private String trueName;
	private String userTel;
	private String userInfo;
	private Integer userFlag;
	private Timestamp registTime;
	private String userImage;
	private Integer deepID;
	private Integer sex;
	private String department;
	private String departName;
	private String userJob;
	private String userMail;
	private Integer companyId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public  String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	public Integer getUserFlag() {
		return userFlag;
	}
	public void setUserFlag(Integer userFlag) {
		this.userFlag = userFlag;
	}
	public Timestamp getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Timestamp registTime) {
		this.registTime = registTime;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public Integer getDeepID() {
		return deepID;
	}
	public void setDeepID(Integer deepID) {
		this.deepID = deepID;
	}
	
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getUserJob() {
		return userJob;
	}
	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", trueName="
				+ trueName + ", userTel=" + userTel + ", userInfo=" + userInfo + ", userFlag=" + userFlag
				+ ", registTime=" + registTime + ", userImage=" + userImage + ", deepID=" + deepID + ", sex=" + sex
				+ ", department=" + department + ", departName=" + departName + ", userJob=" + userJob + ", userMail="
				+ userMail + ", companyId=" + companyId + "]";
	}
	
}
