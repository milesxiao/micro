package me.tdcarefor.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * Created by xsx on 16/12/9.
 */
public class DateTimeUtil {
    /** 比较日期相差几天 start=10月1号 end=10月2号 相差1天 返回1*/
    public static int betweenDays(DateTime start,DateTime end){
        return Days.daysBetween(startOfDay(start),startOfDay(end)).getDays();
    }

    public static int betweenDays(Date start, Date end){
        return Days.daysBetween(startOfDay(new DateTime(start)),startOfDay(new DateTime(end))).getDays();
    }

    /** 获取一天开始的DateTime */
    public static DateTime startOfDay(DateTime dt){
        return dt.withTimeAtStartOfDay();
    }

    /** 获取一天结束的DateTime */
    public static DateTime endOfDay(DateTime dt){
        return dt.millisOfDay().withMaximumValue();
    }

    public static String getTimePeriod(DateTime dt) {
        int hour = dt.getHourOfDay();
        if (hour>0 && hour <= 12) {
            return "上午";
        } else {
            return "下午";
        }
    }

    /**
     * 设置指定时间对象的时分秒
     *
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static DateTime getTimeByHMS(Date date, Integer hour, Integer minute, Integer second) {
        if (date != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            if (hour != null) {
                c.set(Calendar.HOUR_OF_DAY,hour);
            }
            if (minute != null) {
                c.set(Calendar.MINUTE,minute);
            }
            if (second != null) {
                c.set(Calendar.SECOND,second);
            }
            return new DateTime(c);
        }
        return null;
    }

    public static Integer getYYYYmmdd(DateTime dt){
        Integer result=null;
        if(null!=dt){
            result=dt.getYear()*10000+dt.getMonthOfYear()*100+dt.getDayOfMonth();
        }
        return result;
    }

    public static Integer getYYYYmmdd(Date dt){
        Integer result=null;
        if(null!=dt){
            result=getYYYYmmdd(new DateTime(dt));
        }
        return result;
    }

    public static Date parse(String dateStr, String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateStr);
    }

    public static String formatDate(Date d, String format) {
        if(d==null)return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }

    public static void main(String[] args) throws Exception {
        // 1503590400000
//        Date startTime = DateTimeUtil.parse("2017-08-25 00:00:00", "yyyy-MM-dd HH:mm:ss");
//        System.out.println(startTime.getTime());

//        DateTime dt = new DateTime(DateTimeUtil.parse("2017-08-25 00:00:00", "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(dt.withTimeAtStartOfDay().toString("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(dt.millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss"));
    }
}
