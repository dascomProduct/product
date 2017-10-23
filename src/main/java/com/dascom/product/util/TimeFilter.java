package com.dascom.product.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public  class TimeFilter {
	
	/**
	 * 今天凌晨
	 * @return
	 */
	public static Date getDay(){
		Calendar c=getc();
		return c.getTime();
	}
	
	public static Date getTomorrow(){
		Calendar c=getc();
		c.add(Calendar.DAY_OF_MONTH,+1 );
		return c.getTime();
	}
	
	/**
	 * 昨天 凌晨
	 * @return
	 */
	public static Date getYesterday() {
		Calendar c=getc();
	    c.add(Calendar.DAY_OF_MONTH,-1 );
		return c.getTime();
	}
	/**
	 * 本周第一天凌晨，以星期日开始   
	 * @return
	 */
	public static Date getStartWeek() {
		Calendar c = getc();
		c.set(Calendar.DAY_OF_WEEK, 1);
		return c.getTime();
	}
	/**
	 * 本周最后一天凌晨
	 * @return
	 */
	public static Date getLasttWeek() {
		Calendar c = getc();
		c.set(Calendar.DAY_OF_WEEK, 1);
		c.add(Calendar.WEDNESDAY, 1);
		return c.getTime();
	}
	/**
	 * 本月第一天凌晨
	 * @return
	 */
	public static Date getStartMonth() {
		Calendar c = getc();
	    c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}
	/**
	 * 本月最后一天凌晨
	 * @return
	 */
	public static Date getLastMonth() {
		Calendar c = getc();
	    c.add(Calendar.MONTH, 1);
	    c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	private static Calendar getc(){
		Calendar c=Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return  c;
	}
	
	/**
	 * 判断该时间范围内 .list<Date>中符合要求的个数
	 * @return
	 */
	public static int timeFilter(List<Date> listDate,Date startTime ,Date lastTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal=Calendar.getInstance();
		int i=0;
		
		cal.setTime(startTime);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		startTime=cal.getTime();
		System.out.println(sdf.format(startTime));
		
		cal.setTime(lastTime);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, +1);
		lastTime=cal.getTime();
		System.out.println(sdf.format(lastTime));
		
		for (Date date : listDate) {
			
			if(date.getTime()>=startTime.getTime()&&date.getTime()<lastTime.getTime()){
				i++;
			}
		}
		return i;
	}
}
