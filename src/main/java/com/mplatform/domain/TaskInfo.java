package com.mplatform.domain;

import java.sql.Timestamp;

public class TaskInfo {
	private Integer taskId;
	private String taskName;
	private Integer taskManager;
	private String taskMember;
	private String managerName;
	private String memberName;
	private String taskStart;
	private String taskDead;
	private String taskInfo;
	private String taskStatus;
	private String taskTips;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTaskManager() {
		return taskManager;
	}

	public void setTaskManager(Integer taskManager) {
		this.taskManager = taskManager;
	}

	public String getTaskMember() {
		return taskMember;
	}

	public void setTaskMember(String taskMember) {
		this.taskMember = taskMember;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getTaskStart() {
		return taskStart;
	}

	public void setTaskStart(String taskStart) {
		this.taskStart = taskStart;
	}

	public String getTaskDead() {
		return taskDead;
	}

	public void setTaskDead(String taskDead) {
		this.taskDead = taskDead;
	}

	public String getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(String taskInfo) {
		this.taskInfo = taskInfo;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskTips() {
		return taskTips;
	}

	public void setTaskTips(String taskTips) {
		this.taskTips = taskTips;
	}

	@Override
	public String toString() {
		return "TaskInfo [taskId=" + taskId + ", taskName=" + taskName + ", taskManager=" + taskManager
				+ ", taskMember=" + taskMember + ", managerName=" + managerName + ", memberName=" + memberName
				+ ", taskStart=" + taskStart + ", taskDead=" + taskDead + ", taskInfo=" + taskInfo + ", taskStatus="
				+ taskStatus + ", taskTips=" + taskTips + "]";
	}

}
