package com.mplatform.web;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mplatform.domain.CompanyInfo;
import com.mplatform.domain.LevelInfo;
import com.mplatform.domain.UserInfo;
import com.mplatform.service.LevelService;
import com.mplatform.service.UserService;
import com.mplatform.util.CookieUtil;
import com.mplatform.util.TokenUtil;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private LevelService levelService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String user_login_judgement(@RequestBody String body, @ModelAttribute("username") String username,
			@ModelAttribute("password") String password, ModelMap map, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws JSONException {

		JSONObject jo = new JSONObject(body);

		UserInfo user = userService.login(jo.get("username").toString(), jo.get("password").toString());

		if (user == null) {
			map.addAttribute("messageforl", "错误的用户名和密码");
			// return response;
			return "false";
		} else {
			Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					cookie.getName();// get the cookie name
					cookie.getValue(); // get the cookie value
				}
			}

			String cookie = TokenUtil.getInstance().makeToken() + user.getUserId();
			if (CookieUtil.getCookieByName(request, "token") != null)
				System.out.println("token " + cookie);
			System.out.println(cookie);
			// Response res = new Response();
			CookieUtil.addCookie(response, request, "token", cookie, 76800);
			session.setAttribute("token", cookie);
			user.setUserPwd("");
			session.setAttribute("userinfo", user);
			// eturn response;
			return "true";
		}
	}

	@RequestMapping(value = "/userinfo")
	public Map<String, Object> userinfo(HttpServletRequest request) {
		Cookie cookie = CookieUtil.getCookieByName(request, "token");
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> userlist = new HashMap<String, String>();
		if (cookie != null && session.getAttribute("token") != null
				&& (cookie.getValue() == session.getAttribute("token")
						|| cookie.getValue().equals(session.getAttribute("token")))) {
			map.put("success", "true");
			Integer userId = Integer.parseInt(cookie.getValue().substring(24));
			UserInfo user = userService.searchUserById(userId);
			LevelInfo level = levelService.selectLevelById(user.getUserFlag());
			userlist.put("userId", userId.toString());
			userlist.put("trueName", user.getTrueName());
			userlist.put("levelId", user.getUserFlag().toString());
			userlist.put("levelNum", level.getLevelNum());
			map.put("user", userlist);
		} else {
			map.put("success", "false");
		}
		return map;
	}

	@RequestMapping(value = "/logout")
	public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		HttpSession session = request.getSession();
		session.removeAttribute("token");
		session.removeAttribute("userinfo");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", "true");
		map.put("user", null);
		return map;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestBody String body, HttpServletRequest request) throws JSONException {
		JSONObject jo = new JSONObject(body);
		String companyCheck;
		if (jo.has("companyCheck"))
			companyCheck = jo.get("companyCheck").toString();
		else
			companyCheck = "";
		Integer companyId = -1;
		Integer flag;
		if (jo.has("userFlag"))
			flag = Integer.parseInt(jo.get("userFlag").toString());
		else{
			// 创建一个新公司
			if (companyCheck == "new" || companyCheck.equals("new")) {
				String companyName;
				if (jo.has("companyNew"))
					companyName = jo.get("companyNew").toString();
				else
					companyName = "";
				String result = userService.insertCompany(companyName);
				if (result == "success") {
					// 查询新建公司id
					companyId = userService.findCompany(companyName);
					// 设置管理员权限
					// flag = 0;
					// 建立管理员权限以及一个普通用户权限
					if (!levelService.insertStartLevel(companyId))
						return "false";
					// 查询管理员levelId
					flag = levelService.selectLevelId(0, companyId);
				} else if (result == "exist") {
					return "companyExist";
				} else
					return "error";
			}
			// 加入已有公司
			else {
				if (jo.has("companyExist"))
					companyId = Integer.parseInt(jo.get("companyExist").toString());
				// 查询普通用户levelId
				flag = levelService.selectLevelId(1, companyId);
				;
			}
		}
		
		String trueName = jo.get("trueName").toString();
		String userName = jo.get("userName").toString();
		String department;
		if (jo.has("department"))
			department = jo.get("department").toString();
		else
			department = "";
		String email = jo.get("userMail").toString();
		// Integer flag = Integer.parseInt(jo.get("userFlag").toString());
		String job = jo.get("userJob").toString();
		String phone = jo.get("userTel").toString();
		String sex0 = jo.get("sex").toString();
		Integer sex;
		if (sex0.equals("male"))
			sex = 0;
		else
			sex = 1;
		String password;
		if (jo.has("userPwd"))
			password = jo.get("userPwd").toString();
		else {
			password = "admin";
		}
		String imgurl = null;
		if (jo.has("userImage"))
			imgurl = jo.get("userImage").toString();
		boolean result = userService.userRegister(userName, trueName, department, email, flag, job, phone, sex,
				password, imgurl, companyId);
		if (!result)
			return "error";
		else
			return "true";
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public List<UserInfo> selectUser(HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		if (user == null)
			return null;
		Integer page = Integer.parseInt(request.getParameter("_page").toString());
		Integer limit = Integer.parseInt(request.getParameter("_limit").toString());
		Integer count = userService.userCount(user.getCompanyId());
		response.setHeader("x-total-count", count.toString());
		return userService.selectUser(page, limit, user.getCompanyId());
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteUser(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		int result = userService.deleteUser(id);
		if (result == 1)
			return "true";
		else
			return "error";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public List<UserInfo> searchUser(@RequestBody String body, HttpServletRequest request, HttpServletResponse response)
			throws JSONException {
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		if (user == null)
			return null;
		Integer page = Integer.parseInt(request.getParameter("_page").toString());
		Integer limit = Integer.parseInt(request.getParameter("_limit").toString());
		JSONObject jo = new JSONObject(body);
		String prefix = jo.get("prefix").toString();
		String userCondition = jo.get("userCondition").toString();
		Integer count = userService.searchCount(prefix, userCondition, user.getCompanyId());
		response.setHeader("x-total-count", count.toString());
		return userService.searchUser(page, limit, prefix, userCondition, user.getCompanyId());
	}

	@RequestMapping(value = "/allusers", method = RequestMethod.GET)
	public List<UserInfo> allUsers(HttpServletRequest request) {
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		if (user == null)
			return null;
		return userService.allUsers(user.getCompanyId());
	}

	@RequestMapping(value = "/allcompany", method = RequestMethod.GET)
	public List<CompanyInfo> allCompany(HttpServletRequest request) {
		return userService.allCompany();
	}
}