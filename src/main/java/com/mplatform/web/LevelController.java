package com.mplatform.web;

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

import com.mplatform.domain.LevelInfo;
import com.mplatform.domain.UserInfo;
import com.mplatform.service.LevelService;

@RestController
@RequestMapping("/level")
public class LevelController {
	@Autowired
	private LevelService levelService;

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String level(@RequestBody String body,HttpServletRequest request) throws JSONException {
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		JSONObject jo = new JSONObject(body);
		Integer levelId;
		if(jo.has("levelId"))
			levelId = Integer.parseInt(jo.get("levelId").toString());
		else
			levelId = null;
		String levelName = jo.get("levelName").toString();
		String levelNum = jo.get("levelNum").toString();
		
		boolean result = levelService.insertLevel(levelId, levelName, levelNum, user.getCompanyId());
		if (!result)
			return "error";
		else
			return "true";
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public List<LevelInfo> selectLevel(HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		if(user==null) return null;
		Integer page = Integer.parseInt(request.getParameter("_page").toString());
		Integer limit = Integer.parseInt(request.getParameter("_limit").toString());
		Integer count = levelService.levelCount(user.getCompanyId());
		response.setHeader("x-total-count", count.toString());
		return levelService.selectLevel(page, limit, user.getCompanyId());
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteDepart(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		int result = levelService.deleteLevel(id);
		if (result == 1)
			return "true";
		else
			return "error";
	}
	
	@RequestMapping(value = "/alllevel", method = RequestMethod.GET)
	public List<LevelInfo> allLevel(HttpServletRequest request) {
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		if (user == null)
			return null;
		return levelService.allLevel(user.getCompanyId());
	}
}

