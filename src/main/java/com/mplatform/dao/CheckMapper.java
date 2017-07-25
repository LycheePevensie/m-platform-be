package com.mplatform.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mplatform.domain.CheckInfo;
import com.mplatform.domain.DepartInfo;

@Mapper
public interface CheckMapper {

	@Insert("INSERT INTO mp_checkLog(checkUser,checkWay,checkStatus, checkImg, checkTime, checkPlace, outReason, otherReason, recognise) values(#{checkUser},#{checkWay},#{checkStatus},#{checkImg},#{checkTime},#{checkPlace},#{outReason},#{otherReason},#{recognise})")
	int insertCheck(@Param("checkUser") Integer checkUser, @Param("checkWay") Integer checkWay,
			@Param("checkStatus") Integer checkStatus, @Param("checkImg") String checkImg,
			@Param("checkTime") Timestamp checkTime, @Param("checkPlace") String checkPlace,
			@Param("outReason") String outReason, @Param("otherReason") String otherReason,@Param("recognise") Integer recognise);

	@Select("SELECT COUNT(*) FROM mp_checkLog")
	Integer checkCount();

	@Select("SELECT  LIMIT #{start},#{limit}")
	List<CheckInfo> selectCheck(@Param("start") int start, @Param("limit") Integer limit);
	
	/**
	 * 根据用户id查询部门签到信息
	 * @param checkUser
	 * @return
	 */
	@Select("SELECT * FROM mp_department WHERE departId=(SELECT department FROM mp_user WHERE userId = #{checkUser})")
	DepartInfo selectDepartInfo(@Param("checkUser") Integer checkUser);

	@Select("SELECT * FROM mp_checkLog WHERE checkUser=#{userId} AND DATE_FORMAT(checkTime,'%Y-%m-%d')=#{format} AND checkWay=0")
	CheckInfo selectCheckInInfo(@Param("format") String format, @Param("userId") Integer userId);
	
	@Select("SELECT * FROM mp_checkLog WHERE checkUser=#{userId} AND DATE_FORMAT(checkTime,'%Y-%m-%d')=#{format} AND checkWay=1")
	CheckInfo selectCheckOutInfo(@Param("format") String format, @Param("userId") Integer userId);
	
}
