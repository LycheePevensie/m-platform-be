package com.mplatform.service;

import java.util.List;

import com.mplatform.domain.UserInfo;

public interface UserService {
	
	UserInfo login(String userName, String userPwd);

	boolean userRegister(String userName, String trueName, String department, String email, Integer flag, String job,
			String phone, Integer sex, String password, String imgurl);

	List<UserInfo> selectUser(Integer page, Integer limit);

	Integer userCount();

	int deleteUser(Integer id);

	List<UserInfo> searchUser(Integer page, Integer limit, String prefix, String userCondition);

	Integer searchCount(String prefix, String userCondition);

	String postImg(String imgStr, String path);

	boolean deleteImg(String imgurl);

	List<UserInfo> allUsers();


	
	
}
