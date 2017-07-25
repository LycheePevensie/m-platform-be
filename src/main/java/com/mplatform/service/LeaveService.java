package com.mplatform.service;

import java.sql.Timestamp;
import java.util.List;

import com.mplatform.domain.LeaveInfo;

public interface LeaveService {
	boolean insertLeave(Integer leaveUser, Integer leaveManager, Timestamp startTime, Timestamp endTime, String leaveType, String leaveReason,
			String leaveTips);

	Integer leaveCount(Integer userId);

	List<LeaveInfo> selectLeave(Integer page, Integer limit, Integer userId);

	boolean confirmLeave(Integer id);

	boolean rejectLeave(Integer id);

	List<LeaveInfo> selectLeaveByDate(String date, Integer page, Integer limit);

	Integer countByDate(String date);

	Integer countByMonth(String month);

	List<LeaveInfo> selectLeaveByMonth(String month, Integer page, Integer limit);

	Integer countByPeriod(String start, String end);

	List<LeaveInfo> selectLeaveByPeriod(String start, String end, Integer page, Integer limit);
	
}