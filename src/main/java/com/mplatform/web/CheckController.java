//package com.mplatform.web;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mplatform.domain.UserInfo;
//import com.mplatform.service.UserService;
//
//@RestController
//@RequestMapping("/check")
//public class CheckController {
//	@Autowired
//	private CheckService checkService;
//	
//	@RequestMapping(value="/new",method = RequestMethod.POST)
//	public String check(@RequestBody String body)throws JSONException {
//		JSONObject jo=new JSONObject(body);
//		String checkTime = jo.get("checkTime").toString();
//		String checkPlace = jo.get("checkPlace").toString();
//		String outReason = jo.get("outReason").toString();
//		String otherReason = jo.get("otherReason").toString();
//	}
//	
//	@RequestMapping(value="/select",method = RequestMethod.GET)
//	public List<UserInfo> selectUser(HttpServletRequest request,HttpServletResponse response){
//		Integer page = Integer.parseInt(request.getParameter("_page").toString());
//		Integer limit = Integer.parseInt(request.getParameter("_limit").toString());
//		Integer count = checkService.userCount();
//		response.setHeader("x-total-count", count.toString());
//		return checkService.selectUser(page,limit);
//	}
//	
//	@RequestMapping(value="/search",method = RequestMethod.POST)
//	public List<UserInfo> searchUser(@RequestBody String body,HttpServletRequest request,HttpServletResponse response)throws JSONException{
//		Integer page = Integer.parseInt(request.getParameter("_page").toString());
//		Integer limit = Integer.parseInt(request.getParameter("_limit").toString());
//		JSONObject jo=new JSONObject(body);
//		String prefix = jo.get("prefix").toString();
//		String userCondition = jo.get("userCondition").toString();
//		Integer count = checkService.searchCount(prefix,userCondition);
//		response.setHeader("x-total-count", count.toString());
//		return checkService.searchUser(page,limit,prefix,userCondition); 
//	}	
//	
//}
