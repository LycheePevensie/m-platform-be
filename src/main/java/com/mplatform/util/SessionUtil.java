package com.mplatform.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	public static boolean isLogin(HttpServletRequest request){
		HttpSession session = request.getSession();
		Cookie cookie = CookieUtil.getCookieByName(request, "token");
		if(cookie!=null&&session!=null&&cookie.getValue().equals(session.getAttribute("token"))) return true;
		else return false;
	}
}
