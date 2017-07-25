package com.mplatform.service;

import java.sql.Time;
import java.util.List;

import com.mplatform.domain.DepartInfo;

public interface DepartService {

	boolean insertDepart(Integer departId, String department, String departLeader, Time departCheckS, Time departCheckE,
			Time departLeaveS, Time departLeaveE, Integer companyId);
	
	List<DepartInfo> selectDepart(Integer page, Integer limit, Integer integer);

	int deleteDepart(Integer id);

	Integer departCount(Integer integer);

	List<DepartInfo> allDepart(Integer integer);
}
