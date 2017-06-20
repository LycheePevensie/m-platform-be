package com.mplatform.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtil {
	public static Timestamp str2timestamp(String tsStr){
		Timestamp ts = new Timestamp(System.currentTimeMillis());		
		try {
			ts = Timestamp.valueOf(tsStr);
			//System.out.println(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}
	
	public static Time str2time(String tsStr){
		Time ts = new Time(System.currentTimeMillis());		
		try {
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	        ts=new Time(sdf.parse(tsStr+":00").getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}
}
