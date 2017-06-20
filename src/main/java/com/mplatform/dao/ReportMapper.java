package com.mplatform.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mplatform.domain.ReportInfo;

@Mapper
public interface ReportMapper {
	@Insert("INSERT INTO mp_report(reporter, readers, reportDate, reportWeek, reportMonth,reportTime, reportInfo, reportTips, reportImage) values(#{reporter},#{readers},#{reportDate},#{reportWeek},#{reportMonth},#{reportTime},#{reportInfo},#{reportTips},#{reportImage})")
	int insertReport(@Param("reporter") Integer reporter, @Param("readers") String readers,
			@Param("reportDate") String reportDate, @Param("reportWeek") String reportWeek,
			@Param("reportMonth") String reportMonth, @Param("reportTime") Timestamp reportTime,
			@Param("reportInfo") String reportInfo, @Param("reportTips") String reportTips,
			@Param("reportImage") String reportImage);

	@Select("SELECT COUNT(*) FROM mp_report")
	Integer reportCount();

	@Select("SELECT m.* ,p.trueName AS reporterName FROM mp_report m LEFT JOIN mp_user p ON m.reporter = p.userId LIMIT #{start},#{limit}")
	List<ReportInfo> selectReport(@Param("start") int start, @Param("limit") Integer limit);

}
