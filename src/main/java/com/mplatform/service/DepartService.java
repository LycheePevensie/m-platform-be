package com.mplatform.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import com.mplatform.domain.DepartInfo;

public interface DepartService {

	boolean insertDepart(Integer departId, String department, String departLeader, Time departCheckS, Time departCheckE,
			Time departLeaveS, Time departLeaveE);
	
	List<DepartInfo> selectDepart(Integer page, Integer limit);

	int deleteDepart(Integer id);

	Integer departCount();
}
