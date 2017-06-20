package com.mplatform.service;

import java.sql.Timestamp;
import java.util.List;

import com.mplatform.domain.ReportInfo;

public interface ReportService {

	boolean insertReport(Integer reporter, String readers, String reportDateSave, String reportWeekSave,
			String reportMonthSave, Timestamp reportTimestamp, String reportInfo, String reportTips,
			String reportImage);
	
	Integer reportCount();

	List<ReportInfo> selectReport(Integer page, Integer limit);

}
