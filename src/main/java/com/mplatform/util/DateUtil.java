package com.mplatform.util;

import java.sql.Timestamp;

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
}
