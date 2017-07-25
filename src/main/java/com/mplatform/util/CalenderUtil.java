package com.mplatform.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalenderUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	 
//    public static void main(String args[]) {
//        Date d = new Date();
//        // 月初
//        System.out.println("月初" + sdf.format(getMonthStart(d)));
//        // 月末
//        System.out.println("月末" + sdf.format(getMonthEnd(d)));
// 
//        Date date = getMonthStart(d);
//        Date monthEnd = getMonthEnd(d);
//        while (!date.after(monthEnd)) {
//            System.out.println(sdf.format(date));
//            date = getNext(date);
//        }
// 
//    }
 
    public static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index));
        return calendar.getTime();
    }
 
    public static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (-index));
        return calendar.getTime();
    }
 
    public static Date getNext(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }
    
    /** 
    * 判断时间是否在时间段内 
    *  
    * @param date 
    *            当前时间 yyyy-MM-dd HH:mm:ss 
    * @param strDateBegin 
    *            开始时间 00:00:00 
    * @param strDateEnd 
    *            结束时间 00:05:00 
    * @return 
    */  
    public static boolean isInDate(Timestamp date, String strDateBegin,String strDateEnd) {  
	   // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    //String strDate = sdf.format(date);   //2016-12-16 11:53:54
    	String strDate = date.toString();
	    // 截取当前时间时分秒 转成整型
	    int  tempDate=Integer.parseInt(strDate.substring(11, 13)+strDate.substring(14, 16)+strDate.substring(17, 19));  
	    // 截取开始时间时分秒  转成整型
	    int  tempDateBegin=Integer.parseInt(strDateBegin.substring(0, 2)+strDateBegin.substring(3, 5)+strDateBegin.substring(6, 8));  
	    // 截取结束时间时分秒  转成整型
	    int  tempDateEnd=Integer.parseInt(strDateEnd.substring(0, 2)+strDateEnd.substring(3, 5)+strDateEnd.substring(6, 8));
	
	    if ((tempDate >= tempDateBegin && tempDate <= tempDateEnd)) {  
	    return true;  
	    } else {  
	    return false;  
	    }  
    }
    /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

}
