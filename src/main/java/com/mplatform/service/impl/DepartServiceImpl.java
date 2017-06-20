package com.mplatform.service.impl;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mplatform.dao.DepartMapper;
import com.mplatform.domain.DepartInfo;
import com.mplatform.domain.LeaveInfo;
import com.mplatform.service.DepartService;

@Service("departService")
public class DepartServiceImpl implements DepartService {
	@Autowired
	private DepartMapper departMapper;
	
	@Override
	public boolean insertDepart(Integer departId, String department, String departLeader, Time departCheckS, Time departCheckE,
			Time departLeaveS, Time departLeaveE) {
		int result = 0;
		if (departMapper.departExists(departId) == null)
			result = departMapper.insertDepart(department,departLeader,departCheckS,departCheckE,departLeaveS,departLeaveE);
		// 编辑用户
		else {
			result = departMapper.editDepart(departId,department,departLeader,departCheckS,departCheckE,departLeaveS,departLeaveE);
		}
		if (result == 1)
			return true;
		else
			return false;
	}

	@Override
	public List<DepartInfo> selectDepart(Integer page, Integer limit) {
		int start = (page - 1) * limit;
		List<DepartInfo> list = departMapper.selectDepart(start, limit);
		for (DepartInfo info : list) {
			String checks = info.getDepartCheckS().toString().substring(11,16);
			String checke = info.getDepartCheckE().toString().substring(11,16);
			String leaves = info.getDepartLeaveS().toString().substring(11,16);
			String leavee = info.getDepartLeaveE().toString().substring(11,16);
			info.setDepartCheck(checks + " ~ " + checke);
			info.setDepartLeave(leaves + " ~ " + leavee);
		}
		return list;
	}

	@Override
	public int deleteDepart(Integer id) {
		return departMapper.deleteDepart(id);
	}

	@Override
	public Integer departCount() {
		return departMapper.departCount();
	}

}
