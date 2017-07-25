package com.mplatform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mplatform.dao.DepartMapper;
import com.mplatform.dao.UserMapper;
import com.mplatform.domain.CompanyInfo;
import com.mplatform.domain.DepartInfo;
import com.mplatform.domain.UserInfo;
import com.mplatform.service.UserService;
import com.mplatform.util.Base64Helper;
import com.mplatform.util.DeleteFileUtil;
import com.mplatform.util.UUIDHelper;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private DepartMapper departMapper;
	
	@Override
	public UserInfo login(String userName, String userPwd) {
		return userMapper.findUser(userName, userPwd);
	}

	@Override
	public boolean userRegister(String userName, String trueName, String department, String email, Integer flag,
			String job, String phone, Integer sex, String password, String imgurl,Integer companyId) {
		int result = 0;
		// 无照片
		if (imgurl == null) {
			// 新建用户
			if (userMapper.userExists(userName) == null)
				result = userMapper.insertUser(userName, trueName, password, department, email, job, phone, sex, flag,companyId);
			// 编辑用户
			else {
				result = userMapper.editUser(userName, trueName, department, email, job, phone, sex, flag);
			}
		}
		// 有照片
		else {
			if (userMapper.userExists(userName) == null) {
				result = userMapper.insertUserWithImg(userName, trueName, password, department, email, job, phone, sex,
						flag, imgurl,companyId);
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
	public List<UserInfo> selectUser(Integer page, Integer limit,Integer companyId) {
		int start = (page - 1) * limit;
		List<UserInfo> userinfos = userMapper.selectUsers(start, limit, companyId);
		for(UserInfo userinfo:userinfos){
			DepartInfo depart = departMapper.departExists(Integer.parseInt(userinfo.getDepartment()));
			userinfo.setDepartName(depart.getDepartment());
		}
		return userinfos;
	}

	@Override
	public Integer userCount(Integer companyId) {
		return userMapper.userCount(companyId);
	}

	@Override
	public int deleteUser(Integer id) {
		return userMapper.deleteUser(id);
	}

	@Override
	public List<UserInfo> searchUser(Integer page, Integer limit, String prefix, String userCondition, Integer companyId) {
		int start = (page - 1) * limit;
		if (prefix == "name" || prefix.equals("name")) {
			List<UserInfo> userinfos = userMapper.searchUserByName(userCondition, start, limit, companyId);
			for(UserInfo userinfo:userinfos){
				DepartInfo depart = departMapper.departExists(Integer.parseInt(userinfo.getDepartment()));
				userinfo.setDepartName(depart.getDepartment());
			}
			return userinfos;
		} else {
			List<UserInfo> userinfos = userMapper.searchUserByDep(userCondition, start, limit, companyId);
			for(UserInfo userinfo:userinfos){
				DepartInfo depart = departMapper.departExists(Integer.parseInt(userinfo.getDepartment()));
				userinfo.setDepartName(depart.getDepartment());
			}
			return userinfos;
		}
	}

	@Override
	public Integer searchCount(String prefix, String userCondition,Integer companyId) {
		if (prefix == "name") {
			return userMapper.countUserByName(userCondition,companyId);
		} else {
			return userMapper.countUserByDep(userCondition,companyId);
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
	public List<UserInfo> allUsers(Integer companyId) {
		return userMapper.allUsers(companyId);
	}

	@Override
	public UserInfo searchUserById(Integer userId) {
		return userMapper.searchUserById(userId);
	}

	@Override
	public List<CompanyInfo> allCompany() {
		return userMapper.allCompany();
	}

	@Override
	public String insertCompany(String companyName) {
		//若公司名存在
		if(userMapper.companyExist(companyName)!=null) return "exist";
		//否则创建新公司
		else{
			Integer result = userMapper.insertCompany(companyName);
			if(result==1) return "success";
			else return "error";
		}
	}

	@Override
	public Integer findCompany(String companyName) {
		return userMapper.findCompany(companyName);
	}
}
