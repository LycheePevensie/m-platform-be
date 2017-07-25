package com.mplatform.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mplatform.domain.LeaveInfo;

@Mapper
public interface LeaveMapper {

	@Insert("INSERT INTO mp_leaveLog(leaveUser,leaveManager,startTime, endTime, leaveType, leaveReason,leaveTips) values(#{leaveUser},#{leaveManager},#{startTime},#{endTime},#{leaveType},#{leaveReason},#{leaveTips})")
	int insertLeave(@Param("leaveUser") Integer leaveUser, @Param("leaveManager") Integer leaveManager,
			@Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime,
			@Param("leaveType") String leaveType, @Param("leaveReason") String leaveReason,
			@Param("leaveTips") String leaveTips);

	@Select("SELECT m.* ,n.trueName AS userName,p.trueName AS managerName FROM (mp_leaveLog m LEFT JOIN mp_user n ON m.leaveUser = n.userId )LEFT JOIN mp_user p ON m.leaveManager = p.userId WHERE leaveManager=#{userId} OR leaveUser= #{userId} LIMIT #{start},#{limit}")
	List<LeaveInfo> selectLeave(@Param("start") int start, @Param("limit") Integer limit, @Param("userId") Integer userId);

	@Select("SELECT COUNT(*) FROM mp_leaveLog WHERE leaveManager=#{userId} OR leaveUser= #{userId}")
	Integer leaveCount(@Param("userId") Integer userId);

	@Update("UPDATE mp_leaveLog SET leaveStatus=1 WHERE leaveId=#{id}")
	int confirmLeave(@Param("id") Integer id);

	@Update("UPDATE mp_leaveLog SET leaveStatus=2 WHERE leaveId=#{id}")
	int rejectLeave(@Param("id") Integer id);

	@Select("SELECT m.* ,n.trueName AS userName,p.trueName AS managerName FROM (mp_leaveLog m LEFT JOIN mp_user n ON m.leaveUser = n.userId )LEFT JOIN mp_user p ON m.leaveManager = p.userId WHERE DATE(m.startTime)<=#{date} AND DATE(m.endTime)>=#{date} LIMIT #{start},#{limit}")
	List<LeaveInfo> selectLeaveByDate(@Param("date") String date, @Param("start") int start,
			@Param("limit") Integer limit);

	@Select("SELECT COUNT(*) FROM mp_leaveLog m WHERE DATE(m.startTime)<=#{date} AND DATE(m.endTime)>=#{date}")
	Integer countLeaveByDate(@Param("date") String date);

	@Select("SELECT COUNT(*) FROM mp_leaveLog m WHERE MONTH(m.startTime<=#{month} AND MONTH(m.endTime)>=#{month})")
	Integer countLeaveByMonth(@Param("month") String month);

	@Select("SELECT m.* ,n.trueName AS userName,p.trueName AS managerName FROM (mp_leaveLog m LEFT JOIN mp_user n ON m.leaveUser = n.userId )LEFT JOIN mp_user p ON m.leaveManager = p.userId WHERE MONTH(m.startTime)<=#{month} AND MONTH(m.endTime)>=#{month} LIMIT #{start},#{limit}")
	List<LeaveInfo> selectLeaveByMonth(@Param("month") String month, @Param("start") int start, @Param("limit") Integer limit);

	@Select("SELECT COUNT(*) FROM mp_leaveLog m WHERE DATE(m.startTime)<=#{end} AND DATE(m.endTime)>=#{start}")
	Integer countLeaveByPeriod(@Param("start") String start, @Param("end") String end);

	@Select("SELECT m.* ,n.trueName AS userName,p.trueName AS managerName FROM (mp_leaveLog m LEFT JOIN mp_user n ON m.leaveUser = n.userId )LEFT JOIN mp_user p ON m.leaveManager = p.userId WHERE DATE(m.startTime)<=#{selectEnd} AND DATE(m.endTime)>=#{selectStart} LIMIT #{start},#{limit}")
	List<LeaveInfo> selectLeaveByPeriod(@Param("selectStart") String selectStart, @Param("selectEnd") String selectEnd, @Param("start") int start, @Param("limit") Integer limit);
}
