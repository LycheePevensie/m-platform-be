package com.mplatform.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mplatform.domain.CompanyInfo;
import com.mplatform.domain.UserInfo;

@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM mp_user WHERE userName=#{userName} and userPwd=#{userPwd}")
	UserInfo findUser(@Param("userName") String userName,@Param("userPwd") String userPwd);
	
	@Insert("INSERT INTO mp_user(userName,trueName,userPwd,userTel,userFlag,sex,department,userJob,userMail,companyId) values(#{userName},#{trueName},#{password},#{phone},#{flag},#{sex},#{department},#{job},#{email},#{companyId})")
	int insertUser(@Param("userName") String userName, @Param("trueName") String trueName,@Param("password") String password,@Param("department") String department,@Param("email") String email,@Param("job") String job,
			@Param("phone") String phone,@Param("sex") Integer sex,@Param("flag") Integer flag, @Param("companyId") Integer companyId);

	@Select("SELECT userId,userName,trueName,userTel,userFlag,sex,department,userJob,userMail,registTime,deepID,userImage FROM mp_user WHERE companyId=#{companyId} LIMIT #{start},#{limit}")
	List<UserInfo> selectUsers(@Param("start") int start, @Param("limit") Integer limit, @Param("companyId") Integer companyId);
	
	@Select("SELECT COUNT(*) FROM mp_user WHERE companyId=#{companyId}")
	Integer userCount(@Param("companyId") Integer companyId);
	
	@Delete("DELETE FROM mp_user WHERE userId=#{userId}")
	int deleteUser(@Param("userId") Integer id);
	
	@Update("UPDATE mp_user SET userName=#{userName},trueName=#{trueName},userTel=#{phone},userFlag=#{flag},sex=#{sex},department=#{department},userJob=#{job},userMail=#{email} WHERE userName=#{userName}")
	int editUser(@Param("userName") String userName, @Param("trueName") String trueName,@Param("department") String department,@Param("email") String email,@Param("job") String job,
			@Param("phone") String phone,@Param("sex") Integer sex,@Param("flag") Integer flag);
	
	@Select("SELECT * FROM mp_user WHERE userName=#{userName}")
	UserInfo userExists(@Param("userName") String userName);

	@Select("SELECT * FROM mp_user WHERE trueName=#{trueName} AND companyId = #{companyId} LIMIT #{start},#{limit}")
	List<UserInfo> searchUserByName(@Param("trueName") String userCondition, @Param("start") int start, @Param("limit") Integer limit, @Param("companyId") Integer companyId);

	@Select("SELECT * FROM mp_user WHERE department=#{department} AND companyId = #{companyId} LIMIT #{start},#{limit}")
	List<UserInfo> searchUserByDep(@Param("department") String userCondition, @Param("start") int start, @Param("limit") Integer limit, @Param("companyId") Integer companyId);

	@Select("SELECT COUNT(*) FROM mp_user WHERE trueName=#{trueName} AND companyId = #{companyId}")
	Integer countUserByName(@Param("trueName") String userCondition, @Param("companyId") Integer companyId);

	@Select("SELECT COUNT(*) FROM mp_user WHERE department=#{department} AND companyId = #{companyId}")
	Integer countUserByDep(@Param("department") String userCondition, @Param("companyId") Integer companyId);

	@Insert("INSERT INTO mp_user(userName,trueName,userPwd,userTel,userFlag,sex,department,userJob,userMail,userImage,companyId) values(#{userName},#{trueName},#{password},#{phone},#{flag},#{sex},#{department},#{job},#{email},#{imgPath},#{companyId})")
	int insertUserWithImg(@Param("userName") String userName, @Param("trueName") String trueName,@Param("password") String password,@Param("department") String department,@Param("email") String email,
			@Param("job") String job,@Param("phone") String phone,@Param("sex") Integer sex,@Param("flag") Integer flag,@Param("imgPath") String imgPath, @Param("companyId") Integer companyId);
	
	@Select("SELECT userId,trueName FROM mp_user WHERE companyId=#{companyId}")
	List<UserInfo> allUsers(@Param("companyId") Integer companyId);

	@Select("SELECT * FROM mp_user WHERE userId = #{userId}")
	UserInfo searchUserById(@Param("userId") Integer userId);

	@Select("SELECT companyId,companyName FROM mp_company")
	List<CompanyInfo> allCompany();

	@Select("SELECT companyName FROM mp_company WHERE companyName=#{companyName}")
	String companyExist(@Param("companyName") String companyName);

	@Insert("INSERT INTO mp_company(companyName) values(#{companyName})")
	Integer insertCompany(@Param("companyName") String companyName);
	
	@Select("SELECT companyId FROM mp_company WHERE companyName =#{companyName}")
	Integer findCompany(@Param("companyName") String companyName);
}
