package com.mplatform.service;

import java.util.List;

import com.mplatform.domain.CompanyInfo;
import com.mplatform.domain.UserInfo;

public interface UserService {
	
	UserInfo login(String userName, String userPwd);

	boolean userRegister(String userName, String trueName, String department, String email, Integer flag, String job,
			String phone, Integer sex, String password, String imgurl, Integer companyId);

	List<UserInfo> selectUser(Integer page, Integer limit, Integer integer);

	Integer userCount(Integer integer);

	int deleteUser(Integer id);

	List<UserInfo> searchUser(Integer page, Integer limit, String prefix, String userCondition, Integer integer);

	Integer searchCount(String prefix, String userCondition, Integer integer);

	String postImg(String imgStr, String path);

	boolean deleteImg(String imgurl);

	List<UserInfo> allUsers(Integer integer);

	UserInfo searchUserById(Integer userId);

	List<CompanyInfo> allCompany();

	String insertCompany(String companyName);

	Integer findCompany(String companyName);

}
