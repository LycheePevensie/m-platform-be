package com.mplatform.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mplatform.domain.TaskInfo;

@Mapper
public interface TaskMapper {
	@Insert("INSERT INTO mp_task(taskName,taskManager,taskMember,taskStart, taskDead, taskInfo,taskTips) values(#{taskName},#{taskManager},#{taskMember},#{startTime},#{endTime},#{taskInfo},#{taskTips})")
	int insertTask(@Param("taskName") String taskName, @Param("taskManager") Integer taskManager,
			@Param("taskMember") String taskMember, @Param("startTime") Timestamp startTime,
			@Param("endTime") Timestamp endTime, @Param("taskInfo") String taskInfo,
			@Param("taskTips") String taskTips);

	@Select("SELECT COUNT(*) FROM mp_task")
	Integer taskCount();

	@Select("SELECT m.* ,p.trueName AS managerName FROM mp_task m LEFT JOIN mp_user p ON m.taskManager = p.userId LIMIT #{start},#{limit}")
	List<TaskInfo> selectTask(@Param("start") int start, @Param("limit") Integer limit);
}
