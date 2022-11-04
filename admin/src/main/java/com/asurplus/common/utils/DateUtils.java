package com.asurplus.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期时间处理工具类
 *
 * @Author Lizhou
 **/
public class DateUtils {

    /**
     * 获取当前时间，yyyy-MM-dd HH:mm:ss
     */
    public static String getYmdHms() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    /**
     * 时间格式化，yyyy-MM-dd HH:mm:ss
     */
    public static String getYmdHms(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 时间格式化，yyyy-MM-dd HH:mm:ss
     */
    public static Date getYmdHms(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间格式化，yyyy年MM月dd日 HH时mm分ss秒
     */
    public static String getYmdHmsZh() {
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return df.format(new Date());
    }

    /**
     * 时间格式化，yyyy年MM月dd日
     */
    public static String getYmdZh() {
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        return df.format(new Date());
    }

    /**
     * 时间格式化，HH时mm分ss秒
     */
    public static String getHmsZh() {
        DateFormat df = new SimpleDateFormat("HH时mm分ss秒");
        return df.format(new Date());
    }

    /**
     * 时间格式化，yyyyMMdd
     */
    public static String getYyyyMMddHHmmss() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date());
    }

    /**
     * 时间格式化，yyyyMMdd
     */
    public static String getYyyyMMddHHmmss(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(date);
    }

    /**
     * 时间格式化，yyyyMMdd
     */
    public static String getYyyyMMdd() {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    /**
     * 时间格式化，yyyyMMdd
     */
    public static String getYyyyMMdd(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }

    /**
     * 获取当前日期，yyyy-MM-dd
     */
    public static String getYmd() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

    /**
     * 获取当前日期，yyyy-MM-dd
     */
    public static String getYmd(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * 日期格式化，yyyy-MM-dd
     */
    public static Date getYmd(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当月日期，yyyy-MM
     */
    public static String getYm() {
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        return df.format(new Date());
    }

    /**
     * 获取当月日期，yyyy-MM
     */
    public static String getYm(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        return df.format(date);
    }

    /**
     * 日期格式化，yyyy-MM-dd
     */
    public static Date getYm(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当年日期，yyyy
     */
    public static String getY() {
        DateFormat df = new SimpleDateFormat("yyyy");
        return df.format(new Date());
    }

    /**
     * 获取当年日期，yyyy
     */
    public static String getY(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy");
        return df.format(date);
    }

    /**
     * 获取当前时间是本年的第几周
     */
    public static int getWeekNum() {
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(new Date());
        // 获得周数
        return g.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取该日期是本年的第几周
     */
    public static int getWeekNum(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar g = new GregorianCalendar();
        try {
            g.setTime(sdf.parse(date));
            // 获得周数
            return g.get(Calendar.WEEK_OF_YEAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取该日期是本年的第几周
     */
    public static int getWeekNum(Date date) {
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(date);
        // 获得周数
        return g.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取本周周一
     */
    public static String getThisMondayYmd() {
        return getThisMondayYmd(getYmd());
    }


    /**
     * 获取本周周一
     */
    public static String getThisSundayYmd() {
        return getThisSundayYmd(getYmd());
    }

    /**
     * 获取当前日期的周一，yyyy-MM-dd
     */
    public static String getThisMondayYmd(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(date));
            // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
            int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
            if (1 == dayWeek) {
                cal.add(Calendar.DAY_OF_MONTH, -1);
            }
            // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            // 获得当前日期是一个星期的第几天  
            int day = cal.get(Calendar.DAY_OF_WEEK);
            // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
            String imptimeBegin = sdf.format(cal.getTime());
            cal.add(Calendar.DATE, 6);
            return imptimeBegin;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前日期的周日，yyyy-MM-dd
     */
    public static String getThisSundayYmd(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(date));
            // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
            int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
            if (1 == dayWeek) {
                cal.add(Calendar.DAY_OF_MONTH, -1);
            }
            // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            // 获得当前日期是一个星期的第几天  
            int day = cal.get(Calendar.DAY_OF_WEEK);
            // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
            cal.add(Calendar.DATE, 6);
            return sdf.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取本月的天数
     */
    public static int getThisMonthDayNum() {
        return getThisMonthDayNum(getYmd());
    }

    /**
     * 获取某个月的天数
     */
    public static int getThisMonthDayNum(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(date));
            return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取当前月的第一天，yyyy-MM-dd
     */
    public static String getThisMonthFirstDayYmd() {
        return getThisMonthFirstDayYmd(getYm());
    }

    /**
     * 获取当前月的最后一天，yyyy-MM-dd
     */
    public static String getThisMonthLastDayYmd() {
        return getThisMonthLastDayYmd(getYm());
    }

    /**
     * 获取某个月的第一天，yyyy-MM-dd
     */
    public static String getThisMonthFirstDayYmd(String month) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf1.parse(month));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            return sdf2.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取某个月的最后一天，yyyy-MM-dd
     */
    public static String getThisMonthLastDayYmd(String month) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf1.parse(month));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            return sdf2.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取上个月的今天 yyyy-MM-dd
     */
    public static String getPrevMonthDayYmd(int i) {
        return getPrevMonthDayYmd(getYmd(), i);
    }

    /**
     * 获取下个月的今天 yyyy-MM-dd
     */
    public static String getNextMonthDayYmd(int i) {
        return getNextMonthDayYmd(getYmd(), 1);
    }

    /**
     * 获取上个月的今天 yyyy-MM-dd
     */
    public static String getPrevMonthDayYmd(String date, int i) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date resDate = getYmd(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(resDate);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - i);
        resDate = calendar.getTime();
        return dateFormat.format(resDate);
    }

    /**
     * 获取下个月的今天 yyyy-MM-dd
     */
    public static String getNextMonthDayYmd(String date, int i) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date resDate = getYmd(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(resDate);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + i);
        resDate = calendar.getTime();
        return dateFormat.format(resDate);
    }

    /**
     * 获取本月份的上个月 yyyy-MM
     */
    public static String getPrevMonth() {
        return getPrevMonth(getYm());
    }

    /**
     * 获取月份的下个月 yyyy-MM
     */
    public static String getNextMonth() {
        return getNextMonth(getYm());
    }

    /**
     * 获取月份的上个月 yyyy-MM
     */
    public static String getPrevMonth(String month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(month);
            calendar.setTime(date);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
            date = calendar.getTime();
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取月份的下个月 yyyy-MM
     */
    public static String getNextMonth(String month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(month);
            calendar.setTime(date);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
            date = calendar.getTime();
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前季度
     *
     * @return
     */
    public static int getCurrentQuarter() {
        Calendar c = Calendar.getInstance();
        int month = c.get(c.MONTH) + 1;
        if (month >= 1 && month <= 3) {
            return 1;
        }
        if (month >= 4 && month <= 6) {
            return 2;
        }
        if (month >= 7 && month <= 9) {
            return 3;
        }
        return 4;
    }

    /**
     * 获取某季度的第一天和最后一天
     *
     * @param num 第几季度
     */
    public static String[] getQuarterFirstDayAndLastDay(int num) {
        String[] s = new String[2];
        String str = "";
        // 设置本年的季
        Calendar quarterCalendar = null;
        switch (num) {
            case 1: // 本年到现在经过了一个季度，在加上前4个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.MONTH, 3);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = getYmd(quarterCalendar.getTime());
                s[0] = str.substring(0, str.length() - 5) + "01-01";
                s[1] = str;
                break;
            case 2: // 本年到现在经过了二个季度，在加上前三个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.MONTH, 6);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = getYmd(quarterCalendar.getTime());
                s[0] = str.substring(0, str.length() - 5) + "04-01";
                s[1] = str;
                break;
            case 3:// 本年到现在经过了三个季度，在加上前二个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.MONTH, 9);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = getYmd(quarterCalendar.getTime());
                s[0] = str.substring(0, str.length() - 5) + "07-01";
                s[1] = str;
                break;
            case 4:// 本年到现在经过了四个季度，在加上前一个季度
                quarterCalendar = Calendar.getInstance();
                str = getYmd(quarterCalendar.getTime());
                s[0] = str.substring(0, str.length() - 5) + "10-01";
                s[1] = str.substring(0, str.length() - 5) + "12-31";
                break;
        }
        return s;
    }

    /**
     * 获取该日期往后推多少天的日期，yyyy-MM-dd
     * <p>
     * days > 0 ,往后，2020-10-12 --> 2020-10-14
     * days < 0, 往前，2020-10-12 --> 2020-10-10
     */
    public static String getDayBeforeDayYmd(Date date, int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取该日期往后推多少天的日期，yyyy-MM-dd
     * <p>
     * days > 0 ,往后，2020-10-12 --> 2020-10-14
     * days < 0, 往前，2020-10-12 --> 2020-10-10
     */
    public static String getDayBeforeDayYmd(String date, int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        try {
            calendar.setTime(sdf.parse(date));
            // 把日期往后增加一天.整数往后推,负数往前移动
            calendar.add(calendar.DATE, days);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 补全时间，开始时间
     */
    public static String completionTimeStart(String date) {
        return date + " 00:00:00";
    }

    /**
     * 补全时间，结束时间
     */
    public static String completionTimeEnd(String date) {
        return date + " 23:59:59";
    }

    /**
     * 获取两个时间的时间差
     */
    public static String getDateTimeDiffer(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = Math.abs(endDate.getTime() - nowDate.getTime());
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        return day + "天" + hour + "时" + min + "分";
    }

    /**
     * 根据生日算年龄
     */
    private static int getAgeByBirthDay(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            Calendar birth = Calendar.getInstance();
            birth.setTime(sdf.parse(date));
            // 如果传入的时间，在当前时间的后面，返回0岁
            if (birth.after(now)) {
                return 0;
            }
            // 计算年龄
            int age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
            if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                age += 1;
            }
            return age;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取n年后今天的日期 yyyy-MM-dd
     */
    public static String getAfterYearDateYmd(int num) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        // 设置起时间
        cal.setTime(date);
        // 增加一年
        cal.add(Calendar.YEAR, num);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(cal.getTime());
    }

    /**
     * 获得两个时间差多少小时
     */
    public static long getDateDiffHour(Date date1, Date date2) {
        long nh = 1000 * 60 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = date2.getTime() - date1.getTime();
        // 相差多少小时
        return diff / nh;
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        String[] arr = getQuarterFirstDayAndLastDay(getCurrentQuarter());
        for (String item : arr) {
            System.out.println(item);
        }
    }
}
