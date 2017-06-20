package com.mplatform.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mplatform.dao.ReportMapper;
import com.mplatform.domain.ReportInfo;
import com.mplatform.service.ReportService;

@Service("reportService")
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportMapper reportMapper;

	@Override
	public boolean insertReport(Integer reporter, String readers, String reportDate, String reportWeek,
			String reportMonth, Timestamp reportTime, String reportInfo, String reportTips, String reportImage) {
		int result = 0;
		result = reportMapper.insertReport(reporter, readers, reportDate, reportWeek, reportMonth, reportTime,
				reportInfo, reportTips, reportImage);
		if (result == 1)
			return true;
		else
			return false;
	}

	@Override
	public Integer reportCount() {
		return reportMapper.reportCount();
	}

	@Override
	public List<ReportInfo> selectReport(Integer page, Integer limit) {
		int start = (page - 1) * limit;
		List<ReportInfo> list = reportMapper.selectReport(start, limit);
		for (ReportInfo info : list) {
			Timestamp reportTime = info.getReportTime();
			info.setReportTimeStr(reportTime.toString().substring(0, 16));
		}
		return list;
	}

}
