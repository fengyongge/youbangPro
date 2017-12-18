package com.test.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;

public class DateUtil {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(DateUtil.class);

    /**
     * //写字编号/面积编号/交通编码/城市编号/价格编号/租赁编号
     **/
    public static String Serialnumber() {
        SimpleDateFormat sdf = new SimpleDateFormat("sSS");
        String str = sdf.format(new Date());
        return str;
    }

    /**
     * jin+产生订单号+序列号
     */
    public static String idrand() {
        //SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSS");
        SimpleDateFormat sdf = new SimpleDateFormat("ssSS");
        String str = sdf.format(new Date());
        return str;
    }

    //年月日小时分钟秒 返回字符串类型
    public static String Symdhms() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(new Date());
        return str;
    }
    
  //年月日 返回字符串类型
    public static String Symd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(new Date());
        return str;
    }

    //年月日小时分钟秒返回 date 类型
    public static Date Dateymdhms() {
        Date endDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar date = Calendar.getInstance();
            date.setTime(new Date());
            date.set(Calendar.DATE, date.get(Calendar.DATE));
            endDate = sdf.parse(sdf.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }

    public static String Sdatetring(Date sdate) throws Exception {
        String sDate = null;
        try {
            //SimpleDateFormat sdf = new SimpleDateFormat (sdate,Locale.US);
            //Date date=sdf.parse(sdate);
            SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sDate = sdff.format(sdate);
            //System.out.println("时间日期字符转换:"+sDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sDate;
    }

    /**
     * 时间延长3分钟时候进行发送
     **/
    public static boolean Sdate(String date1, Date sdate) throws Exception {
        String sDate = null;
        SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(sdate);
        nowTime.add(Calendar.MINUTE, 5);
        sDate = sdff.format(nowTime.getTime());
        if (date1.equals(sDate)) {
            return true;
        } else {
            return false;
        }
    }

    public static String Ridrand() {
        //SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSS");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss=ssSS");
        String str = sdf.format(new Date());
        return str;
    }

    //jiachenghao 暂时解日期有.0问题
    public static String date2str(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateformat.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        return dateformat.format(date);
    }
    
    
    //jiachenghao 暂时解日期有.0问题
    public static String string2str(String date) {
       String dateResult=null;
    	
    	if (date == null) {
            return "1900-01-01 11:10:10";
        }

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateformat.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        try {
        	dateResult= dateformat.format(dateformat.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateResult;
        
    }
    
    
    //jiachenghao 暂时解日期有.0问题
    public static String string2strdate(String date) {
       String dateResult=null;
    	
    	if (date == null) {
            return "1900-01-01";
        }

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        dateformat.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        try {
        	dateResult= dateformat.format(dateformat.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateResult;
        
    }

    //计算两个日期间的毫秒
    public static float betweenS(String bDate, String oDate) {

        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        float between = 0;
        try {
            java.util.Date begin = dfs.parse(bDate);
            java.util.Date end = dfs.parse(oDate);
            between = NumberUtil.getFloat((float) ((end.getTime() - begin.getTime()) / 1000), 3);// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return between;

    }

    /**
     * date转Timestamp
     * @param date
     * @return
     */
    public static Timestamp date2Timestamp(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return Timestamp.valueOf(sdf.format(date));
    }

    /**
     * date必须是yyyy-MM-dd HH:mm:ss格式
     * @param date
     * @return
     */
    public static Timestamp String2Timestamp(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = null;
        try {
            timestamp = Timestamp.valueOf(sdf.format(sdf.parse(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    public static Timestamp String2Timestamp2(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Timestamp timestamp = null;
        try {
            timestamp = Timestamp.valueOf(sdf.format(sdf.parse(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }
    /**
     * 统计两个时间之间的天数间隔，包含开始日期和截止日期
     * @param begin_time
     * @param end_time
     * @eg yyyy-MM-dd
     * @return List<String> 存放两个时间之间的每一天
     */
    public static List<String> dateCount1(String begin_time,String end_time){
    	List<String> dateList = new ArrayList<>();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  	Calendar calendar = Calendar.getInstance();
	  	//设置截止日期
	  	String end_date = end_time.substring(0, 10);
	  	try {
	  		//设置开始日期
	  		calendar.setTime(sdf.parse(begin_time));
	  		String date = sdf.format(calendar.getTime());
	  		dateList.add(date);
	  		//若开始日期不等于截止日期，则继续添加进dateList中
	  		while(!date.equals(end_date)){
	  			calendar.add(Calendar.DATE, + 1);  
	  		  	date = sdf.format(calendar.getTime());
	  		  	dateList.add(date);
	  		}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return dateList;
    }
    
    /**
     * 统计两个时间之间的天数间隔，包含开始日期和截止日期
     * @param begin_time
     * @param end_time 为null时默认当前月份
     * @eg yyyy-MM-dd
     * @return List<String> 存放两个时间之间的每一天
     */
    public static List<String> monthCount1(String begin_time,String end_time){
    	List<String> dateList = new ArrayList<>();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	  	Calendar calendar = Calendar.getInstance();
	  	//设置截止日期
	  	if(end_time==null){
	  		end_time = sdf.format(new Date());
	  	}
	  	try {
	  		//设置开始日期
	  		calendar.setTime(sdf.parse(begin_time));
	  		String date = sdf.format(calendar.getTime());
	  		dateList.add(date);
	  		//若开始日期不等于截止日期，则继续添加进dateList中
	  		while(!date.equals(end_time)){
	  			calendar.add(Calendar.MONTH, + 1);  
	  		  	date = sdf.format(calendar.getTime());
	  		  	dateList.add(date);
	  		}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return dateList;
    }
    
    /**
     * 
     * description: 获取指定年月的天数
     *
     * @author jiachenghao	
     * @param month
     * @return
     */
    public static int maxDays(String month){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
    	Calendar calendar = new GregorianCalendar(); 
    	Date date1;
		try {
			date1 = sdf.parse(month);
			calendar.setTime(date1); //放入你的日期 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 统计两个时间之间的天数间隔，包含开始日期和截止日期
     * @param begin_time
     * @param end_time
     * @eg yyyy-MM-dd HH:mm:ss
     * @return List<String> 存放两个时间之间的每一天
     */
    public static List<String> dateCount2(String begin_time,String end_time){
    	List<String> dateList = new ArrayList<>();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  	Calendar calendar = Calendar.getInstance();
	  	//设置截止日期
	  	String end_date = end_time.substring(0, 10);
	  	try {
	  		//设置开始日期
	  		calendar.setTime(sdf.parse(begin_time));
	  		String date = sdf.format(calendar.getTime());
	  		dateList.add(begin_time);
	  		//若开始日期不等于截止日期，则继续添加进dateList中
	  		while(!date.equals(end_date)){
	  			calendar.add(Calendar.DATE, + 1);  
	  		  	date = sdf.format(calendar.getTime());
	  		  	dateList.add(date+" 00:00:00");
	  		}
	  		if(!dateList.get(dateList.size()-1).equals(end_time)){
	  			dateList.add(end_time);
	  		}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return dateList;
    }
    
    /**
     * 两个日期类型的字符串比较
     * @param before
     * @param after
     * @return 若before在after之前，返回true，否则返回false，若传入的字符串格式不对，直接返回false
     */
    public static boolean beforeTo(String before,String after){
    	//若为空，直接返回false
    	if(!StringValidateUtil.isNotEmpty(before)||!StringValidateUtil.isNotEmpty(after)){
    		return false;
    	}
    	DateFormat df = null;
    	try {
    		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		Date dt1 = df.parse(before);
			Date dt2 = df.parse(after);
			if(dt1.getTime()<dt2.getTime()){
				return true;
			}
		} catch (ParseException e) {
			//若出异常，则尝试另一种格式进行转换
			try {
				df = new SimpleDateFormat("yyyy-MM-dd");
				Date dt1 = df.parse(before);
				Date dt2 = df.parse(after);
				if(dt1.getTime()<dt2.getTime()){
					return true;
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
				//若再次报异常，则直接返回false
				return false;
			}
		}
    	return false;
    }
    
    /**
     * 获取当天的开始时间 比如2017-03-06 00:00:00
     * @param date
     * @return
     */
    public static String getTodayStartTime() {
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    	  Calendar todayStart = Calendar.getInstance();  
          todayStart.set(Calendar.HOUR_OF_DAY, 0);  
          todayStart.set(Calendar.MINUTE, 0);  
          todayStart.set(Calendar.SECOND, 0);  
          todayStart.set(Calendar.MILLISECOND, 0);  
		  return sdf.format(todayStart.getTime());  
    }
    /**
     * 获取前一天的开始时间 
     * @param date
     * @return
     */
    public static String getYesterdayStartTime() {
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    	  Calendar todayStart = Calendar.getInstance();
    	  todayStart.add(Calendar.DAY_OF_MONTH, -1); 
          todayStart.set(Calendar.HOUR_OF_DAY, 0);  
          todayStart.set(Calendar.MINUTE, 0);  
          todayStart.set(Calendar.SECOND, 0);  
          todayStart.set(Calendar.MILLISECOND, 0);   
		  return sdf.format(todayStart.getTime());  
    }
    /**
     * 获取前一天的时间 
     * @param date
     * @return
     */
    public static String getYesterdayTime() {
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    	  Calendar todayStart = Calendar.getInstance();
    	  todayStart.add(Calendar.DAY_OF_MONTH, -1);  
          System.out.println(sdf.format(todayStart.getTime()));  
		  return sdf.format(todayStart.getTime());  
    }
    
    /**
     * 
     */
    public static String getTomorrow(String today){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(today));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE, + 1);
        String tomorrow = sdf.format(calendar.getTime());
        return tomorrow;
    }
    
    
    /**
     * 获取N天前或N天后的日期
     * @param n>0 N天后，n < 0 N天前
     * @return
     */
    public static String getDateFrontOrBehindToday(int n) {
  	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
  	  Calendar todayStart = Calendar.getInstance();
  	  todayStart.add(Calendar.DAY_OF_MONTH, n);  
	  return sdf.format(todayStart.getTime());  
    }
    
    /**
     * 获取月份的第一天
     * @param date
     * @return
     */
    public static String getFirstDayOfMonth(String date){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    	Calendar calendar = Calendar.getInstance();
    	try {
			calendar.setTime(sdf.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH, 1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return sdf.format(calendar.getTime());
    }
    /**
     * 获取月份的最后一天
     * @param date
     * @return
     */
    public static String getLastDayOfMonth(String date){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    	Calendar calendar = Calendar.getInstance();
    	try {
			calendar.setTime(sdf.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return sdf.format(calendar.getTime());
    }
    
    public static String getFirstDayOfYear(int year){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    	Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
    	return sdf.format(calendar.getTime());
    }
    public static String getLastDayOfYear(int year){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    	Calendar calendar = Calendar.getInstance();
    	calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
    	return sdf.format(calendar.getTime());
    }
    
    public static Date string2Date(String str){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		 Date date = sdf.parse(str);
    		 return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return null;
    }
    public static String date2str(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateformat = new SimpleDateFormat(format);
        return dateformat.format(date);
    }
    
    public static Date string2Date(String str, String format){
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	try {
    		 Date date = sdf.parse(str);
    		 return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 获取两个日期之间的日期-包含起始日期和结束日期
     * @param start 开始日期
     * @param end 结束日期
     * @return 日期集合
     */
    public static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        result.add(tempStart.getTime());
        tempStart.add(Calendar.DAY_OF_YEAR, 1);
        
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        if(!start.equals(end)){
        	result.add(tempEnd.getTime());
        }
        return result;
    }
    
    /**
     * 获取两个日期之间的日期-包含起始日期和结束日期
     * @param start 开始日期
     * @param end 结束日期
     * @return 之间的相差天数
     */
    public static int getBetweenDays(Date start, Date end)  {
    	long intervalMilli = start.getTime() - end.getTime();
    	int a = (int) (intervalMilli / (24 * 60 * 60 * 1000));
        return a;
    }
    
    public static String getDateFromlong(long time){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return format.format(time);
    }

    /**
     * 根据时间段遍历每一天
     * @author   fengyongge@ediankai.com
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 含字符串的数组list
     */
    public static List<String> display(String startTime, String endTime){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list = new ArrayList<>();

        try{
            Date dateOne = dateFormat.parse(startTime);
            Date dateTwo = dateFormat.parse(endTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateOne);
            while(calendar.getTime().before(dateTwo)){
                list.add(dateFormat.format(calendar.getTime()));

                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            list.add(endTime);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return  list;
    }



    /**
     * 测试程序
     *
     * @return
     * @throws ParseException 
     **/

    public static void main(String[] args) throws ParseException {
//    	System.out.println(getDateFromlong(System.currentTimeMillis()));
    	String d1 = "2015-04-10 05:00:00";
//    	String d2 = "2017-05-14 05:00:00";
//    	List<String> dateCount = DateUtil.monthCount1(d1, null);
//    	for (String string : dateCount) {
//			System.out.println(string);
//		}
    	int num = maxDays(d1);
    	System.out.println(num);

    }
}
