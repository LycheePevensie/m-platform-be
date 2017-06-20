package com.mplatform.dao;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mplatform.domain.DepartInfo;
import com.mplatform.domain.UserInfo;

@Mapper
public interface DepartMapper {
	
	@Select("SELECT * FROM mp_department WHERE departId=#{departId}")
	DepartInfo departExists(@Param("departId") Integer departId);
	
	@Insert("INSERT INTO mp_department(department,departLeader,departCheckS,departCheckE,departLeaveS,departLeaveE) values(#{department},#{departLeader},#{departCheckS},#{departCheckE},#{departLeaveS},#{departLeaveE})")
	int insertDepart(@Param("department") String department,@Param("departLeader") String departLeader, @Param("departCheckS") Time departCheckS, @Param("departCheckE") Time departCheckE,
			@Param("departLeaveS") Time departLeaveS, @Param("departLeaveE") Time departLeaveE);

	@Update("UPDATE mp_department SET department=#{department},departLeader=#{departLeader},departCheckS=#{departCheckS},departCheckE=#{departCheckE},departLeaveS=#{departLeaveS},departLeaveE=#{departLeaveE} WHERE departId=#{departId}")
	int editDepart(@Param("departId") Integer departId,@Param("department") String department,@Param("departLeader") String departLeader, @Param("departCheckS") Time departCheckS, @Param("departCheckE") Time departCheckE,
			@Param("departLeaveS") Time departLeaveS, @Param("departLeaveE") Time departLeaveE);
	
	@Select("SELECT m.* ,p.trueName AS departLeaderName FROM mp_department m LEFT JOIN mp_user p ON m.departLeader = p.userId LIMIT #{start},#{limit}")
	List<DepartInfo> selectDepart(@Param("start") int start, @Param("limit") Integer limit);
	
	@Select("SELECT COUNT(*) FROM mp_department")
	Integer departCount();
	
	@Delete("DELETE FROM mp_department WHERE departId=#{departId}")
	int deleteDepart(@Param("departId") Integer id);
}
