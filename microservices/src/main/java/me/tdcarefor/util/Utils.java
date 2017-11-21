package me.tdcarefor.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by yw on 16/11/18.
 */
public class Utils {

    /**
     * 计算年龄,返回年龄字符串，如果小于2岁，会加上月份，比如25岁，1岁4个月,大于2岁的默认超过6个月算加一岁，即四舍五入，小于28天为新生儿
     *
     * @param bornTime
     * @return
     */
    public static String computingAge(Date bornTime) {
        if (bornTime == null) {
            return "";
        }
        Calendar now = Calendar.getInstance();  //获取当前时间
        if (now.getTime().before(bornTime)) {
            //出生日期在当前日期之后，错误
            return "出生日期大于当前日期";
        }
        Calendar born = Calendar.getInstance();
        born.setTime(bornTime);
        StringBuffer sb = new StringBuffer();
        //相差天数
        int ageDay = new Long((now.getTime().getTime() - bornTime.getTime()) / (1000 * 60 * 60 * 24)).intValue();
        if (ageDay < 28) {//如果小于28天，新生儿
            return "新生儿";
        }
        //年份差
        int bornYear = born.get(Calendar.YEAR);
        int nowYear = now.get(Calendar.YEAR);
        int ageYear = nowYear - bornYear;
        //月份差
        int bornMonth = born.get(Calendar.MONTH);
        int nowMonth = now.get(Calendar.MONTH);
        int ageMonth = nowMonth - bornMonth;
        if (nowMonth < bornMonth) {
            ageYear -= 1;
            ageMonth += 12;
        }
        //如果年龄大于2，月份超过6个月，年龄加1
        if (ageYear > 2 && ageMonth > 6) {
            ageYear += 1;
        }
        if (ageYear > 0) sb.append(ageYear + "岁");
        if (ageYear < 2 && ageMonth >= 1) sb.append(ageMonth + "个月");
        return sb.toString();
    }
}
