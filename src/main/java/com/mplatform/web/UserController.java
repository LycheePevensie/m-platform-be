package com.mplatform.web;


import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

import com.mplatform.domain.UserInfo;
import com.mplatform.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController{
	@Autowired
	private UserService userService;

	@RequestMapping(value="",method = RequestMethod.POST)
	public String user_login_judgement(@RequestBody String body,@ModelAttribute("username") String username,@ModelAttribute("password") String password, ModelMap map,HttpSession session,HttpServletRequest request) throws JSONException {
    	
		JSONObject jo=new JSONObject(body);
			
    	UserInfo user = userService.login(jo.get("username").toString(),jo.get("password").toString());
		if (user==null) {
			map.addAttribute("messageforl","错误的用户名和密码");
			return "false";
		}
		else {
			session.setAttribute("userfornow",user);
			return "true";
		}
	}
	@RequestMapping(value="/login")
	public String login(){
		return "user_login";
	}
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String register(@RequestBody String body,HttpServletRequest request)throws JSONException {
		JSONObject jo=new JSONObject(body);
		String trueName = jo.get("trueName").toString();
		String userName = jo.get("userName").toString();
		String department = jo.get("department").toString();
		String email = jo.get("userMail").toString();
		Integer flag = Integer.parseInt(jo.get("userFlag").toString());
		String job = jo.get("userJob").toString();
		String phone = jo.get("userTel").toString();
		String sex0 = jo.get("sex").toString();
		Integer sex;
		if(sex0.equals("male")) sex = 0;
		else sex = 1;
		String password;
		if(jo.has("userPwd")) password = jo.get("userPwd").toString();
		else{
			password = "admin";
		}
		String imgurl = null;
		if(jo.has("userImage")) imgurl = jo.get("userImage").toString();
		boolean result = userService.userRegister(userName,trueName,department,email,flag,job,phone,sex,password,imgurl);
		if(!result) return "error";
		else return "success";
	}
	@RequestMapping(value="/select",method = RequestMethod.GET)
	public List<UserInfo> selectUser(HttpServletRequest request,HttpServletResponse response){
		Integer page = Integer.parseInt(request.getParameter("_page").toString());
		Integer limit = Integer.parseInt(request.getParameter("_limit").toString());
		Integer count = userService.userCount();
		response.setHeader("x-total-count", count.toString());
		return userService.selectUser(page,limit);
	}
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String deleteUser(HttpServletRequest request){
		Integer id = Integer.parseInt(request.getParameter("id"));
		int result = userService.deleteUser(id);
		if(result==1) return "success";
		else return "error"; 
	}
	@RequestMapping(value="/search",method = RequestMethod.POST)
	public List<UserInfo> searchUser(@RequestBody String body,HttpServletRequest request,HttpServletResponse response)throws JSONException{
		Integer page = Integer.parseInt(request.getParameter("_page").toString());
		Integer limit = Integer.parseInt(request.getParameter("_limit").toString());
		JSONObject jo=new JSONObject(body);
		String prefix = jo.get("prefix").toString();
		String userCondition = jo.get("userCondition").toString();
		Integer count = userService.searchCount(prefix,userCondition);
		response.setHeader("x-total-count", count.toString());
		return userService.searchUser(page,limit,prefix,userCondition); 
	}
	@RequestMapping(value="/allusers",method = RequestMethod.GET)
	public List<UserInfo> allUsers(HttpServletRequest request){
		return userService.allUsers();
	}
	
}