package com.mplatform.service.impl;

import java.sql.Timestamp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mplatform.dao.TaskMapper;
import com.mplatform.domain.TaskInfo;
import com.mplatform.service.TaskService;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskMapper taskMapper;
	
	@Override
	public boolean insertTask(String taskName, Integer taskManager, String taskMember, Timestamp startTime,
			Timestamp endTime, String taskInfo, String taskTips) {
		int result=0;
		result = taskMapper.insertTask(taskName, taskManager, taskMember, startTime,
			 endTime, taskInfo, taskTips);
		if(result==1) return true;
		else return false;
	}

	@Override
	public Integer taskCount() {
		return taskMapper.taskCount();
	}

	@Override
	public List<TaskInfo> selectTask(Integer page, Integer limit) {
		int start = (page - 1) * limit;
		return taskMapper.selectTask(start, limit);
	}

}
