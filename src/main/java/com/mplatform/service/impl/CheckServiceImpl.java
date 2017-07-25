package com.mplatform.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mplatform.dao.CheckMapper;
import com.mplatform.domain.CheckInfo;
import com.mplatform.domain.DepartInfo;
import com.mplatform.domain.LeaveInfo;
import com.mplatform.service.CheckService;
import com.mplatform.util.CalenderUtil;

@Service("checkService")
public class CheckServiceImpl implements CheckService {
	@Autowired
	private CheckMapper checkMapper;

	@Override
	public boolean insertCheck(Integer checkUser, Integer checkWay, String checkImg,
			Timestamp checkTime, String checkPlace, String outReason, String otherReason, Integer recognise) {
		DepartInfo info = checkMapper.selectDepartInfo(checkUser);
		String startT;
		String endT;
		Integer checkStatus=-1;
		//签到
		if(checkWay==0){
			startT = info.getDepartCheckS().toString();
			endT = info.getDepartCheckE().toString();
		}
		//签退
		else{
			startT = info.getDepartLeaveS().toString();
			endT = info.getDepartLeaveE().toString();
		}
		//System.out.println("start: "+startT+"endT: "+endT+"checkTime: "+checkTime);
		boolean status = CalenderUtil.isInDate(checkTime, startT, endT);
		if(!status){
			checkStatus = checkWay==0?1:2;
		}
		else{
			checkStatus = 0;
		}
		int result = 0;
		result = checkMapper.insertCheck(checkUser, checkWay, checkStatus, checkImg, checkTime, checkPlace, outReason,
				otherReason,recognise);
		if (result == 1)
			return true;
		else
			return false;
	}

	@Override
	public Integer checkCount() {
		return checkMapper.checkCount();
	}

	@Override
	public List<Map<String,Object>> selectCheck(Integer userId, Integer page, Integer limit) {
//		int start = (page - 1) * limit;
//		List<CheckInfo> list = checkMapper.selectCheck(start, limit);
//		for (CheckInfo info : list) {
//			String time = info.getCheckTime().toString();
//			String week = info.getCheckTime().toString();
//			info.setCheckDate(time.substring(0, 10));
//			info.setCheckWeek(week);
//		}
//		return list;
		int start = (page - 1) * limit;
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 月初
		//System.out.println("月初" + sdf.format(getMonthStart(d)));
		// 月末
		//System.out.println("月末" + sdf.format(getMonthEnd(d)));
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Date date = CalenderUtil.getMonthStart(d);
		Date monthEnd = CalenderUtil.getMonthEnd(d);
		//Integer checkWay;
		while (!date.after(monthEnd)) {
			//checkWay=0;
			Map<String,Object> map = new HashMap<String,Object>();
			CheckInfo checkIn = checkMapper.selectCheckInInfo(sdf.format(date),userId);
			map.put("checkIn", checkIn);
			CheckInfo checkOut = checkMapper.selectCheckOutInfo(sdf.format(date),userId);
			map.put("checkOut", checkOut);			
			map.put("checkDate", sdf.format(date));
			map.put("checkWeek", CalenderUtil.getWeekOfDate(date));
			list.add(map);
			System.out.println(sdf.format(date));  
		    date = CalenderUtil.getNext(date);
		}
		return list;
	}

}
