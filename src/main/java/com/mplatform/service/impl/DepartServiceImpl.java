package com.mplatform.service.impl;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mplatform.dao.DepartMapper;
import com.mplatform.domain.DepartInfo;
import com.mplatform.service.DepartService;

@Service("departService")
public class DepartServiceImpl implements DepartService {
	@Autowired
	private DepartMapper departMapper;

	@Override
	public boolean insertDepart(Integer departId, String department, String departLeader, Time departCheckS,
			Time departCheckE, Time departLeaveS, Time departLeaveE, Integer companyId) {
		int result = 0;
		if (departMapper.departExists(departId) == null)
			result = departMapper.insertDepart(department, departLeader, departCheckS, departCheckE, departLeaveS,
					departLeaveE, companyId);
		// 编辑用户
		else {
			result = departMapper.editDepart(departId, department, departLeader, departCheckS, departCheckE,
					departLeaveS, departLeaveE, companyId);
		}
		if (result == 1)
			return true;
		else
			return false;
	}

	@Override
	public List<DepartInfo> selectDepart(Integer page, Integer limit, Integer companyId) {
		int start = (page - 1) * limit;
		List<DepartInfo> list = departMapper.selectDepart(start, limit, companyId);
		for (DepartInfo info : list) {
			String checks = info.getDepartCheckS().toString().substring(0, 5);
			String checke = info.getDepartCheckE().toString().substring(0, 5);
			String leaves = info.getDepartLeaveS().toString().substring(0, 5);
			String leavee = info.getDepartLeaveE().toString().substring(0, 5);
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
	public Integer departCount(Integer companyId) {
		return departMapper.departCount(companyId);
	}

	@Override
	public List<DepartInfo> allDepart(Integer companyId) {
		return departMapper.allDepart(companyId);
	}

}
