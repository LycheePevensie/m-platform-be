package com.mplatform.service;

import java.sql.Timestamp;
import java.util.List;

import com.mplatform.domain.LeaveInfo;

public interface LeaveService {
	boolean insertLeave(Integer leaveUser, Integer leaveManager, Timestamp startTime, Timestamp endTime, String leaveType, String leaveReason,
			String leaveTips);

	Integer leaveCount();

	List<LeaveInfo> selectLeave(Integer page, Integer limit);

	boolean confirmLeave(Integer id);

	boolean rejectLeave(Integer id);
	
}