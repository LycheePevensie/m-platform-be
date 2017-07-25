package com.mplatform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mplatform.dao.LevelMapper;
import com.mplatform.domain.LevelInfo;
import com.mplatform.service.LevelService;

@Service("levelService")
public class LevelServiceImpl implements LevelService {

	@Autowired
	private LevelMapper levelMapper;
	
	@Override
	public boolean insertLevel(Integer levelId, String levelName, String levelNum,Integer companyId) {
		int result = 0;
		if (levelMapper.levelExists(levelId) == null)
			result = levelMapper.insertLevel(levelName,levelNum,companyId);
		// 编辑用户
		else {
			result = levelMapper.editLevel(levelId, levelName, levelNum);
		}
		if (result == 1)
			return true;
		else
			return false;
	}


	@Override
	public List<LevelInfo> selectLevel(Integer page, Integer limit, Integer companyId) {
		int start = (page - 1) * limit;
		List<LevelInfo> list = levelMapper.selectLevel(start, limit, companyId);
		return list;
	}

	@Override
	public int deleteLevel(Integer id) {
		return levelMapper.deleteLevel(id);
	}


	@Override
	public Integer levelCount(Integer companyId) {
		return levelMapper.levelCount(companyId);
	}


	@Override
	public LevelInfo selectLevelById(Integer userFlag) {
		return levelMapper.selectLevelById(userFlag);
	}


	@Override
	public boolean insertStartLevel(Integer companyId) {
		Integer result = levelMapper.insertNewLevel("管理员", "0",companyId);
		if(result!=1) return false;
		result = levelMapper.insertNewLevel("普通用户", "1",companyId);
		if(result!=1) return false;
		return true;
	}


	@Override
	public Integer selectLevelId(Integer levelNum, Integer companyId) {
		return levelMapper.selectLevelId(levelNum,companyId);
	}


	@Override
	public List<LevelInfo> allLevel(Integer companyId) {
		return levelMapper.allLevel(companyId);
	}

}
