package com.mplatform.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mplatform.dao.UserMapper;
import com.mplatform.domain.UserInfo;
import com.mplatform.service.UserService;
import com.mplatform.util.Base64Helper;
import com.mplatform.util.DeleteFileUtil;
import com.mplatform.util.UUIDHelper;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserInfo login(String userName, String userPwd) {
		return userMapper.findUser(userName, userPwd);
	}

	@Override
	public boolean userRegister(String userName, String trueName, String department, String email, Integer flag,
			String job, String phone, Integer sex, String password, String imgurl) {
		int result = 0;
		// 无照片
		if (imgurl == null) {
			// 新建用户
			if (userMapper.userExists(userName) == null)
				result = userMapper.insertUser(userName, trueName, password, department, email, job, phone, sex, flag);
			// 编辑用户
			else {
				result = userMapper.editUser(userName, trueName, department, email, job, phone, sex, flag);
			}
		}
		// 有照片
		else {
			if (userMapper.userExists(userName) == null) {
				result = userMapper.insertUserWithImg(userName, trueName, password, department, email, job, phone, sex,
						flag, imgurl);
			}
			// 编辑用户
			else {

				// 图片旧路径查询
				// 删除旧路径的图片
				// 数据库里修改为新路径
				result = userMapper.editUser(userName, trueName, department, email, job, phone, sex, flag);
				// 图片保存到新路径
			}
		}
		if (result == 1)
			return true;
		else
			return false;
	}

	@Override
	public List<UserInfo> selectUser(Integer page, Integer limit) {
		int start = (page - 1) * limit;
		return userMapper.selectUsers(start, limit);
	}

	@Override
	public Integer userCount() {
		return userMapper.userCount();
	}

	@Override
	public int deleteUser(Integer id) {
		return userMapper.deleteUser(id);
	}

	@Override
	public List<UserInfo> searchUser(Integer page, Integer limit, String prefix, String userCondition) {
		int start = (page - 1) * limit;
		if (prefix == "name" || prefix.equals("name")) {
			return userMapper.searchUserByName(userCondition, start, limit);
		} else {
			return userMapper.searchUserByDep(userCondition, start, limit);
		}
	}

	@Override
	public Integer searchCount(String prefix, String userCondition) {
		if (prefix == "name") {
			return userMapper.countUserByName(userCondition);
		} else {
			return userMapper.countUserByDep(userCondition);
		}
	}

	@Override
	public String postImg(String imgStr, String path) {
		String uuid = UUIDHelper.generateShortUuid();
		String imgPath = path+uuid+".jpg";
		Base64Helper.GenerateImage(imgStr, imgPath);
		return "/images/"+uuid+".jpg";
	}

	@Override
	public boolean deleteImg(String fileName) {
		return DeleteFileUtil.deleteFile(fileName);
	}

	@Override
	public List<UserInfo> allUsers() {
		return userMapper.allUsers();
	}
}
