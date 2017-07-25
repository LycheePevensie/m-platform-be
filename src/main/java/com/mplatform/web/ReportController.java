package com.mplatform.web;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mplatform.domain.ReportInfo;
import com.mplatform.service.ReportService;
import com.mplatform.util.DateUtil;

@RestController
@RequestMapping("/report")
public class ReportController {
	@Autowired
	private ReportService reportService;

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String report(@RequestBody String body) throws JSONException {
		JSONObject jo = new JSONObject(body);
		Integer reporter = Integer.parseInt(jo.get("reporter").toString());
		String readers = jo.get("readers").toString();
		String reportDate = "";
		if (jo.has("reportDate"))
			reportDate = jo.get("reportDate").toString();
		String reportWeek = "";
		if (jo.has("reportWeek"))
			reportWeek = jo.get("reportWeek").toString();
		String reportMonth = "";
		if (jo.has("reportMonth"))
			reportMonth = jo.get("reportMonth").toString();
		String reportTime = jo.get("reportTime").toString();
		String reportInfo = jo.get("reportInfo").toString();
		String reportTips = "";
		if (jo.has("reportTips"))
			reportTips = jo.get("reportTips").toString();
		String reportImage = jo.get("reportImage").toString();
		// time格式处理
		String timeTemp = reportTime.substring(0, reportTime.length() - 5).replace("T", " ");
		Timestamp reportTimestamp = DateUtil.str2timestamp(timeTemp);
		String reportDateSave = (!reportDate.equals("")) ? reportDate.substring(0, 10) : "";
		String reportWeekStart = (!reportWeek.equals("")) ? reportWeek.split(",")[0].substring(2, 12) : "";
		String reportWeekEnd = (!reportWeek.equals("")) ? reportWeek.split(",")[1].substring(1, 11) : "";
		String reportWeekSave = (!reportWeek.equals("")) ? (reportWeekStart + " ~ " + reportWeekEnd) : "";
		String reportMonthSave = (!reportMonth.equals("")) ? reportMonth.substring(0, 7) : "";

		boolean result = reportService.insertReport(reporter, readers, reportDateSave, reportWeekSave, reportMonthSave,
				reportTimestamp, reportInfo, reportTips, reportImage);
		if (!result)
			return "error";
		else
			return "true";
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public List<ReportInfo> selectTask(HttpServletRequest request, HttpServletResponse response) {
		Integer page = Integer.parseInt(request.getParameter("_page").toString());
		Integer limit = Integer.parseInt(request.getParameter("_limit").toString());
		Integer count = reportService.reportCount();
		response.setHeader("x-total-count", count.toString());
		return reportService.selectReport(page, limit);
	}
}