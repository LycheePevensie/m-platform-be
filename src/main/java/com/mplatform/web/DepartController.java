package com.mplatform.web;

import java.sql.Time;
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

import com.mplatform.domain.DepartInfo;
import com.mplatform.service.DepartService;
import com.mplatform.util.DateUtil;

@RestController
@RequestMapping("/department")
public class DepartController {
	@Autowired
	private DepartService departService;

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String check(@RequestBody String body) throws JSONException {
		JSONObject jo = new JSONObject(body);
		Integer departId;
		if(jo.has("departId"))
			departId = Integer.parseInt(jo.get("departId").toString());
		else
			departId = null;
		String department = jo.get("department").toString();
		String departLeader = jo.get("departLeader").toString();
		String checkS = jo.get("departCheckS").toString();
		String checkE = jo.get("departCheckE").toString();
		String leaveS = jo.get("departLeaveS").toString();
		String leaveE = jo.get("departLeaveE").toString();
		System.out.println(checkS);
		// time格式处理 2017-06-20T17:05:51.561Z
		String checkSTemp = checkS.substring(11, checkS.length() - 8);
		String checkETemp = checkE.substring(11, checkE.length() - 8);
		String leaveSTemp = leaveS.substring(11, leaveS.length() - 8);
		String leaveETemp = leaveE.substring(11, leaveE.length() - 8);
		Time departCheckS = DateUtil.str2time(checkSTemp);
		Time departCheckE = DateUtil.str2time(checkETemp);
		Time departLeaveS = DateUtil.str2time(leaveSTemp);
		Time departLeaveE = DateUtil.str2time(leaveETemp);
		boolean result = departService.insertDepart(departId, department, departLeader, departCheckS, departCheckE,
				departLeaveS, departLeaveE);
		if (!result)
			return "error";
		else
			return "success";
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public List<DepartInfo> selectDepart(HttpServletRequest request, HttpServletResponse response) {
		Integer page = Integer.parseInt(request.getParameter("_page").toString());
		Integer limit = Integer.parseInt(request.getParameter("_limit").toString());
		Integer count = departService.departCount();
		response.setHeader("x-total-count", count.toString());
		return departService.selectDepart(page, limit);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteDepart(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		int result = departService.deleteDepart(id);
		if (result == 1)
			return "success";
		else
			return "error";
	}

}
