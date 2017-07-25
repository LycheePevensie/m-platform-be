package com.mplatform.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mplatform.service.CheckService;
import com.mplatform.util.CookieUtil;
import com.mplatform.util.DateUtil;
import com.mplatform.util.SessionUtil;

@RestController
@RequestMapping("/check")
public class CheckController {
	@Autowired
	private CheckService checkService;

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String check(HttpServletRequest request, @RequestBody String body) throws JSONException {
		JSONObject jo = new JSONObject(body);
		String recognise0 = jo.get("recognise").toString();
		Integer recognise;
		if (recognise0.equals("true"))
			recognise = 0;
		else
			recognise = 1;
		String checkWay0 = jo.get("checkWay").toString();
		Integer checkWay;
		if (checkWay0.equals("checkin"))
			checkWay = 0;
		else
			checkWay = 1;
		String checkImg;
		if (jo.has("checkImg"))
			checkImg = jo.get("checkImg").toString();
		else
			checkImg = null;
		String time = jo.get("checkTime").toString();
		String checkPlace = jo.get("checkPlace").toString();
		String outReason = jo.get("outReason").toString();
		String otherReason = jo.get("otherReason").toString();
		// time格式处理
		String timeTemp = time.substring(0, time.length() - 5).replace("T", " ");
		System.out.println(timeTemp);
		Timestamp checkTime = DateUtil.str2timestamp(timeTemp);
		Cookie cookie = CookieUtil.getCookieByName(request, "token");
		String UserId = cookie.getValue();
		System.out.println(UserId);
		Integer checkUser = Integer.parseInt(UserId.substring(24, UserId.length()));
		System.out.println(checkUser);
		boolean result = checkService.insertCheck(checkUser, checkWay, checkImg, checkTime, checkPlace, outReason,
				otherReason,recognise);
		if (!result)
			return "error";
		else
			return "true";
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public List<Map<String,Object>> selectCheck(HttpServletRequest request, HttpServletResponse response) {
		Integer page = Integer.parseInt(request.getParameter("_page").toString());
		Integer limit = Integer.parseInt(request.getParameter("_limit").toString());
		//Integer count = checkService.checkCount();
		Integer userId = CookieUtil.getCookieUserId(request);
		if(SessionUtil.isLogin(request)&&userId!=null){
			List<Map<String,Object>> all = checkService.selectCheck(userId, page, limit);
			response.setHeader("x-total-count", all.size()+"");
			int start = (page-1)*limit;
			List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
			for(int i=start;i<start+limit;i++){
				if(i>=all.size()) break;
				result.add(all.get(i));
			}
			return result;
		}
		else{
			response.setHeader("message", "timeout");
			return null;
		}
	}

//	@RequestMapping(value = "/search", method = RequestMethod.POST)
//	public List<UserInfo> searchUser(@RequestBody String body, HttpServletRequest request, HttpServletResponse response)
//			throws JSONException {
//		Integer page = Integer.parseInt(request.getParameter("_page").toString());
//		Integer limit = Integer.parseInt(request.getParameter("_limit").toString());
//		JSONObject jo = new JSONObject(body);
//		String prefix = jo.get("prefix").toString();
//		String userCondition = jo.get("userCondition").toString();
//		Integer count = checkService.searchCount(prefix, userCondition);
//		response.setHeader("x-total-count", count.toString());
//		return checkService.searchUser(page, limit, prefix, userCondition);
//	}

}
