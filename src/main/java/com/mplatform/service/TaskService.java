package com.mplatform.service;

import java.sql.Timestamp;
import java.util.List;

import com.mplatform.domain.TaskInfo;

public interface TaskService {

	boolean insertTask(String taskName, Integer taskManager, String taskMember, Timestamp startTime, Timestamp endTime,
			String taskInfo, String taskTips);

	Integer taskCount();

	List<TaskInfo> selectTask(Integer page, Integer limit);
}