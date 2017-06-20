package com.mplatform.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mplatform.domain.UserInfo;

@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM mp_user WHERE userName=#{userName} and userPwd=#{userPwd}")
	UserInfo findUser(@Param("userName") String userName,@Param("userPwd") String userPwd);
	
	@Insert("INSERT INTO mp_user(userName,trueName,userPwd,userTel,userFlag,sex,department,userJob,userMail) values(#{userName},#{trueName},#{password},#{phone},#{flag},#{sex},#{department},#{job},#{email})")
	int insertUser(@Param("userName") String userName, @Param("trueName") String trueName,@Param("password") String password,@Param("department") String department,@Param("email") String email,@Param("job") String job,
			@Param("phone") String phone,@Param("sex") Integer sex,@Param("flag") Integer flag);

	@Select("SELECT userId,userName,trueName,userTel,userFlag,sex,department,userJob,userMail,registTime,deepID,userImage FROM mp_user LIMIT #{start},#{limit}")
	List<UserInfo> selectUsers(@Param("start") int start, @Param("limit") Integer limit);
	
	@Select("SELECT COUNT(*) FROM mp_user")
	Integer userCount();
	
	@Delete("DELETE FROM mp_user WHERE userId=#{userId}")
	int deleteUser(@Param("userId") Integer id);
	
	@Update("UPDATE mp_user SET userName=#{userName},trueName=#{trueName},userTel=#{phone},userFlag=#{flag},sex=#{sex},department=#{department},userJob=#{job},userMail=#{email} WHERE userName=#{userName}")
	int editUser(@Param("userName") String userName, @Param("trueName") String trueName,@Param("department") String department,@Param("email") String email,@Param("job") String job,
			@Param("phone") String phone,@Param("sex") Integer sex,@Param("flag") Integer flag);
	
	@Select("SELECT * FROM mp_user WHERE userName=#{userName}")
	UserInfo userExists(@Param("userName") String userName);

	@Select("SELECT * FROM mp_user WHERE trueName=#{trueName} LIMIT #{start},#{limit}")
	List<UserInfo> searchUserByName(@Param("trueName") String userCondition, @Param("start") int start, @Param("limit") Integer limit);

	@Select("SELECT * FROM mp_user WHERE department=#{department} LIMIT #{start},#{limit}")
	List<UserInfo> searchUserByDep(@Param("department") String userCondition, @Param("start") int start, @Param("limit") Integer limit);

	@Select("SELECT COUNT(*) FROM mp_user WHERE trueName=#{trueName}")
	Integer countUserByName(@Param("trueName") String userCondition);

	@Select("SELECT COUNT(*) FROM mp_user WHERE department=#{department}")
	Integer countUserByDep(@Param("department") String userCondition);

	@Insert("INSERT INTO mp_user(userName,trueName,userPwd,userTel,userFlag,sex,department,userJob,userMail,userImage) values(#{userName},#{trueName},#{password},#{phone},#{flag},#{sex},#{department},#{job},#{email},#{imgPath})")
	int insertUserWithImg(@Param("userName") String userName, @Param("trueName") String trueName,@Param("password") String password,@Param("department") String department,@Param("email") String email,
			@Param("job") String job,@Param("phone") String phone,@Param("sex") Integer sex,@Param("flag") Integer flag,@Param("imgPath") String imgPath);
	
	@Select("SELECT userId,trueName FROM mp_user")
	List<UserInfo> allUsers();
}
