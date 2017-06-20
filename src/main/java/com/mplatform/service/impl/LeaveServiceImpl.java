package com.mplatform.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mplatform.dao.LeaveMapper;
import com.mplatform.domain.LeaveInfo;
import com.mplatform.service.LeaveService;

@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	private LeaveMapper leaveMapper;

	@Override
	public boolean insertLeave(Integer leaveUser, Integer leaveManager, Timestamp startTime, Timestamp endTime,
			String leaveType, String leaveReason, String leaveTips) {
		int result = 0;
		System.out.println(leaveType);
		result = leaveMapper.insertLeave(leaveUser, leaveManager, startTime, endTime, leaveType, leaveReason,
				leaveTips);
		if (result == 1)
			return true;
		else
			return false;
	}

	@Override
	public List<LeaveInfo> selectLeave(Integer page, Integer limit) {
		int start = (page - 1) * limit;
		List<LeaveInfo> list = leaveMapper.selectLeave(start, limit);
		for (LeaveInfo info : list) {
			String startT = info.getStartTime().toString();
			String endT = info.getEndTime().toString();
			info.setLeaveTime(
					startT.substring(0, startT.length() - 5) + " 至 " + endT.substring(0, startT.length() - 5));
		}
		return list;
	}

	@Override
	public Integer leaveCount() {
		return leaveMapper.leaveCount();
	}

	@Override
	public boolean confirmLeave(Integer id) {
		int result = 0;
		result = leaveMapper.confirmLeave(id);
		if (result == 1)
			return true;
		else
			return false;
	}

	@Override
	public boolean rejectLeave(Integer id) {
		int result = 0;
		result = leaveMapper.rejectLeave(id);
		if (result == 1)
			return true;
		else
			return false;
	}

	// @Override
	// public List<LeaveInfo> searchLeave(Integer page, Integer limit, String
	// searchWay, String userCondition) {
	// int start = (page-1)*limit;
	// if(searchWay=="name"||searchWay.equals("name")){
	// System.out.println(searchWay);
	// return leaveMapper.searchUserByName(userCondition,start,limit);
	// }
	// else{
	// System.out.println("here");
	// System.out.println(prefix);
	// return leaveMapper.searchUserByDep(userCondition,start,limit);
	// }
	// }
	//
	// @Override
	// public Integer searchCount(String searchWay, String userCondition) {
	// if(searchWay=="name"){
	// return leaveMapper.countUserByName(userCondition);
	// }
	// else{
	// return leaveMapper.countUserByDep(userCondition);
	// }
	// }
}
