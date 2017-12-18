package com.zzti.youbang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


public class TimeUtils {
	
/**
	 * 处理时间
	 * 
	 * @param string
	 * @return
	 */
	public static String handTime(String time) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtils.isEmpty(time)) {
			return "";
		}
		try {

			Date date = format.parse(time);
			long tm = System.currentTimeMillis();// 当前时间戳
			long tm2 = date.getTime();// 发表动态的时间戳
			long d = (tm - tm2) / 1000;// 时间差距 单位秒
			if ((d / (60 * 60 * 24)) > 1) {
				return time.substring(0, 10);
			} else if ((d / (60 * 60 * 24)) > 0) {
				return "昨天";
			} else if ((d / (60 * 60)) > 0) {
				return d / (60 * 60) + "小时前";
			} else if ((d / 60) > 0) {
				return d / 60 + "分钟前";
			} else {
				// return d + "秒前";
				return "刚刚";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	
	
	public static String getTime(long time){
		String sb;
	    Date dat=new Date(time);
	    GregorianCalendar gc = new GregorianCalendar();
	    gc.setTime(dat);  
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    sb=format.format(gc.getTime());  
	    return sb;
	}
	
	
	
}