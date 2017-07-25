package com.mplatform.service;

import java.util.List;

import com.mplatform.domain.LevelInfo;

public interface LevelService {

	boolean insertLevel(Integer levelId, String levelName, String levelNum, Integer integer);

	Integer levelCount(Integer integer);

	List<LevelInfo> selectLevel(Integer page, Integer limit, Integer integer);

	int deleteLevel(Integer id);

	LevelInfo selectLevelById(Integer userFlag);

	boolean insertStartLevel(Integer companyId);

	Integer selectLevelId(Integer levelNum,Integer companyId);

	List<LevelInfo> allLevel(Integer companyId);
}
