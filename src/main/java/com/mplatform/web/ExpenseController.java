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

import com.mplatform.domain.ExpenseInfo;
import com.mplatform.service.ExpenseService;
import com.mplatform.util.DateUtil;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
	@Autowired
	private ExpenseService expenseService;

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String check(@RequestBody String body) throws JSONException {
		JSONObject jo = new JSONObject(body);
		String expenseName = jo.get("expenseName").toString();
		Integer expenseUser = Integer.parseInt(jo.get("expenseUser").toString());
		Integer expenseManager = Integer.parseInt(jo.get("expenseManager").toString());
		Double expenseValue = Double.parseDouble(jo.get("expenseValue").toString());
		String tempTime = jo.get("expenseTime").toString();
		String expenseTips = jo.get("expenseTips").toString();
		System.out.println(tempTime);
		// time格式处理
		String formatTime = tempTime.substring(1, tempTime.length() - 6).replace("T", " ");
		Timestamp expenseTime = DateUtil.str2timestamp(formatTime);

		boolean result = expenseService.insertExpense(expenseName, expenseUser, expenseManager, expenseValue,
				expenseTime, expenseTips);
		if (!result)
			return "error";
		else
			return "success";
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public List<ExpenseInfo> selectExpense(HttpServletRequest request, HttpServletResponse response) {
		Integer page = Integer.parseInt(request.getParameter("_page").toString());
		Integer limit = Integer.parseInt(request.getParameter("_limit").toString());
		Integer count = expenseService.expenseCount();
		response.setHeader("x-total-count", count.toString());
		return expenseService.selectExpense(page, limit);
	}
	
	@RequestMapping(value = "/confirm",method = RequestMethod.GET)
	public String confirmExpense(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id").toString());
		boolean result = expenseService.confirmExpense(id);
		if(!result) return "error";
		else return "success";
	}
	
	@RequestMapping(value = "/reject",method = RequestMethod.GET)
	public String rejectExpense(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id").toString());
		boolean result = expenseService.rejectExpense(id);
		if(!result) return "error";
		else return "success";
	}
	//
	// @RequestMapping(value="/search",method = RequestMethod.POST)
	// public List<LeaveInfo> searchLeave(@RequestBody String
	// body,HttpServletRequest request,HttpServletResponse response)throws
	// JSONException{
	// Integer page =
	// Integer.parseInt(request.getParameter("_page").toString());
	// Integer limit =
	// Integer.parseInt(request.getParameter("_limit").toString());
	// JSONObject jo=new JSONObject(body);
	// String searchWay = jo.get("searchWay").toString();
	// String userCondition = jo.get("userCondition").toString();
	// Integer count = leaveService.searchCount(searchWay,userCondition);
	// response.setHeader("x-total-count", count.toString());
	// return leaveService.searchLeave(page,limit,searchWay,userCondition);
	// }

}
