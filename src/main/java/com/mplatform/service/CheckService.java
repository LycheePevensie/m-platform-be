package com.mplatform.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.mplatform.domain.CheckInfo;

public interface CheckService {
	boolean insertCheck(Integer checkUser, Integer checkWay, String checkImg, Timestamp checkTime,
			String checkPlace, String outReason, String otherReason,Integer recognise);

	Integer checkCount();

	List<Map<String,Object>> selectCheck(Integer userId, Integer page, Integer limit);
	
}
