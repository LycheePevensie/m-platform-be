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

import com.mplatform.domain.LeaveInfo;
import com.mplatform.service.LeaveService;
import com.mplatform.util.DateUtil;

@RestController
@RequestMapping("/leave")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;
	
	@RequestMapping(value="/new",method = RequestMethod.POST)
	public String check(@RequestBody String body)throws JSONException {
		JSONObject jo=new JSONObject(body);
		Integer leaveUser = Integer.parseInt(jo.get("leaveUser").toString());
		Integer leaveManager = Integer.parseInt(jo.get("leaveManager").toString());
		String leaveTime = jo.get("leaveTime").toString();
		String leaveType = jo.get("leaveType").toString();
		String leaveReason = jo.get("leaveReason").toString();
		String leaveTips = jo.get("leaveTips").toString();
		System.out.println(leaveTime);
		//time格式处理
		String startTemp = leaveTime.split(",")[0];
		String endTemp = leaveTime.split(",")[1];
		String start = startTemp.substring(2, startTemp.length()-6).replace("T", " ");
		String end = endTemp.substring(1, endTemp.length()-7).replace("T", " ");
		Timestamp startTime = DateUtil.str2timestamp(start);
		Timestamp endTime = DateUtil.str2timestamp(end);
		
		boolean result = leaveService.insertLeave(leaveUser,leaveManager,startTime,endTime,leaveType,leaveReason,leaveTips);
		if(!result) return "error";
		else return "success";
	}
	
	@RequestMapping(value="/select",method = RequestMethod.GET)
	public List<LeaveInfo> selectLeave(HttpServletRequest request,HttpServletResponse response){
		Integer page = Integer.parseInt(request.getParameter("_page").toString());
		Integer limit = Integer.parseInt(request.getParameter("_limit").toString());
		Integer count = leaveService.leaveCount();
		response.setHeader("x-total-count", count.toString());
		return leaveService.selectLeave(page,limit);
	}
	
	@RequestMapping(value = "/confirm",method = RequestMethod.GET)
	public String confirmLeave(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id").toString());
		boolean result = leaveService.confirmLeave(id);
		if(!result) return "error";
		else return "success";
	}
	
	@RequestMapping(value = "/reject",method = RequestMethod.GET)
	public String rejectLeave(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id").toString());
		boolean result = leaveService.rejectLeave(id);
		if(!result) return "error";
		else return "success";
	}
//	
//	@RequestMapping(value="/search",method = RequestMethod.POST)
//	public List<LeaveInfo> searchLeave(@RequestBody String body,HttpServletRequest request,HttpServletResponse response)throws JSONException{
//		Integer page = Integer.parseInt(request.getParameter("_page").toString());
//		Integer limit = Integer.parseInt(request.getParameter("_limit").toString());
//		JSONObject jo=new JSONObject(body);
//		String searchWay = jo.get("searchWay").toString();
//		String userCondition = jo.get("userCondition").toString();
//		Integer count = leaveService.searchCount(searchWay,userCondition);
//		response.setHeader("x-total-count", count.toString());
//		return leaveService.searchLeave(page,limit,searchWay,userCondition); 
//	}	
	
}
