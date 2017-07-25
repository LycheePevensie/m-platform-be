package com.mplatform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mplatform.domain.LevelInfo;

@Mapper
public interface LevelMapper {

	@Select("SELECT * FROM mp_level WHERE levelId=#{levelId}")
	LevelInfo levelExists(@Param("levelId") Integer levelId);

	@Insert("INSERT INTO mp_level(levelName, levelNum, companyId) values(#{levelName},#{levelNum},#{companyId})")
	int insertLevel(@Param("levelName") String levelName, @Param("levelNum") String levelNum, @Param("companyId") Integer companyId);

	@Update("UPDATE mp_level SET levelId=#{levelId},levelName=#{levelName},levelNum=#{levelNum} WHERE levelId=#{levelId}")
	int editLevel(@Param("levelId") Integer levelId, @Param("levelName") String levelName,
			@Param("levelNum") String levelNum);

	@Delete("DELETE FROM mp_level WHERE levelId=#{levelId}")
	int deleteLevel(@Param("id") Integer id);

	@Select("SELECT COUNT(*) FROM mp_level WHERE companyId = #{companyId}")
	Integer levelCount(@Param("companyId") Integer companyId);

	@Select("SELECT * FROM mp_level WHERE companyId = #{companyId} LIMIT #{start},#{limit}")
	List<LevelInfo> selectLevel(@Param("start") int start, @Param("limit") Integer limit, @Param("companyId") Integer companyId);

	@Select("SELECT * FROM mp_level WHERE levelId = #{levelId}")
	LevelInfo selectLevelById(@Param("levelId") Integer userFlag);
	
	@Insert("INSERT INTO mp_level(levelName, levelNum, companyId) values(#{levelName},#{levelNum},#{companyId})")
	Integer insertNewLevel(@Param("levelName") String levelName, @Param("levelNum") String levelNum, @Param("companyId") Integer companyId);

	@Select("SELECT * FROM mp_level WHERE levelNum=#{levelNum} AND companyId = #{companyId}")
	Integer selectLevelId(@Param("levelNum") Integer levelNum, @Param("companyId") Integer companyId);

	@Select("SELECT levelId,levelName FROM mp_level WHERE companyId=#{companyId}")
	List<LevelInfo> allLevel(Integer companyId);

}
