package com.mplatform.web;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mplatform.domain.JSONHelper;
import com.mplatform.service.UserService;

@RestController
@RequestMapping("/img")
public class ImgController {
	
	@Value("${imgpath}")
	private String imgpath;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/",method = RequestMethod.POST)
	public String test(HttpServletRequest request){
		return "true";
	}
	
	@RequestMapping(value="/imgpost",method = RequestMethod.POST)
	public JSONHelper test2(@RequestBody String body,HttpServletRequest request){
		System.out.println(body);
//		//通过流方式获取
//        StringBuilder sb = new StringBuilder();  
//        try(BufferedReader reader = request.getReader();) {  
//                 char[] buff = new char[1024];  
//                 int len;  
//                 while((len = reader.read(buff)) != -1) {  
//                          sb.append(buff,0, len);  
//                 }  
//        }catch (IOException e) {  
//                 e.printStackTrace();  
//        }
        String imgurl = userService.postImg(body,imgpath);
        JSONHelper jh = new JSONHelper();
        jh.setImgurl(imgurl);
		return jh;
	}
	
	@RequestMapping(value="/imgdel",method = RequestMethod.POST)
	public String test3(@RequestBody String body,HttpServletRequest request)throws JSONException{
		JSONObject jo=new JSONObject(body);
		String imgurl = jo.get("imagePath").toString();
		if(userService.deleteImg("/Users/lychee/m-platform-be/src/main/resources/static"+imgurl)) return "true";
		else return "false";
	}	
}
