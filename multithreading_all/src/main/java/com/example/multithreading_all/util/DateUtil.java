package com.example.multithreading_all.util;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    /**
     * 日期显示格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_MONTH_FORMAT = "yyyy-MM";
    public static final String DEFAULT_YEAR_FORMAT = "yyyy";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATETIME_MINTIME = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_DATE_FORMAT_CH = "yyyy年MM月dd日";
    public static final String DEFAULT_DATETIME_FORMAT_CH = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String DEFAULT_DATETIME_FORMAT_HALFCH = "yyyy年MM月dd日 HH:mm:ss";
    public static final String DEFAULT_YEAR_MONTH_MM_SS_CH = "MM月dd日 HH时mm分";
    public static final String DEFAULT_MONTH_MM = "MM月dd日";



    /**
     * 日期入库格式
     */
    public static final String DEFAULT_DATE_FORMAT_SHT = "yyyyMMdd";
    public static final String DEFAULT_DATETIME_FORMAT_SHT = "yyyyMMddHHmmss";
    public static final String DEFAULT_DATETIME_MINTIME_SHT = "yyyyMMddHHmm";
    
    
    /** 时间 **/
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /**
     * 时间格式类型
     */
    public static final int DATE_TYPE_MONTH = 0;  //yyyy-MM
    public static final int DATE_TYPE_DEFAULT = 1;  //yyyy-MM-dd
    public static final int DATE_TYPE_TIMESTAMP = 2; //yyyy-MM-dd HH:mm:ss
    public static final int DATE_TYPE_MINTIME = 3; //yyyy-MM-dd HH:mm
    public static final int DATE_TYPE_DEFAULT_CH = 4; //yyyy年MM月dd日
    public static final int DATE_TYPE_TIMESTAMP_CH = 5; //yyyy年MM月dd日 HH时mm分ss秒
    
    /**
     * 判别标准
     */
    public static final String TIME_FORMAT_CH = "CH";  //中国的星期标准
    public static final String TIME_FORMAT_US = "US";  //国外的星期标准
    
    /**
     * 一天中开始时间和结束时间
     */
    public static final String DAY_START_TIME = "00:00:00";
    public static final String DAY_END_TIME = "23:59:59";
    
    /**
     * 私有构造方法，禁止对该类进行实例化
     */
    private DateUtil() {
    }

    /**
     * 得到系统当前日期时间
     * 
     * @return 当前日期时间
     */
    public static Date getNow() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 得到用缺省方式格式化的当前日期
     * 
     * @return 当前日期
     */
    public static String getDate() {
        return getDateTime(DEFAULT_DATE_FORMAT);
    }
    
    public static String getDate(String formatter) {
        SimpleDateFormat format = new SimpleDateFormat(formatter);
        return format.format(getNow());
    }

    /**
     * 得到用缺省方式格式化的当前日期及时间
     * 
     * @return 当前日期及时间
     */
    public static String getDateTime() {
        return getDateTime(DEFAULT_DATETIME_FORMAT);
    }

    public static String getYearTime() {
        return getDateTime(DEFAULT_YEAR_FORMAT);
    }



    /**
     * 得到系统当前日期及时间，并用指定的方式格式化
     * 
     * @param pattern 显示格式
     * @return 当前日期及时间
     */
    public static String getDateTime(String pattern) {
        Date datetime = Calendar.getInstance().getTime();
        return getDateTime(datetime, pattern);
    }

    /**
     * 得到用指定方式格式化的日期
     * 
     * @param date 需要进行格式化的日期
     * @param pattern 显示格式
     * @return 日期时间字符串
     */
    public static String getDateTime(Date date, String pattern) {
        if (null == pattern || "".equals(pattern)) {
            pattern = DEFAULT_DATETIME_FORMAT;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }
    
    /**
     * 毫秒转时间格式
     * @param millisecond 毫秒
     * @param pattern    显示格式
     * @return 日期时间字符串
     */
    public static String long2DateTime(long millisecond, String pattern){
        Date date = new Date();
        date.setTime(millisecond);  
        return DateUtil.getDateTime(date, pattern);
    }

    /**
     * 内部方法。为指定日期增加相应的天数或月数
     *
     * @param date
     *            基准日期
     * @param amount
     *            增加的数量
     * @param field
     *            增加的单位，年，月或者日
     * @return 增加以后的日期
     */
    public static Date add(Date date, int amount, int field) {
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTime(date);
        calendar.add(field, amount);
        
        return calendar.getTime();
    }

    public static String add(String date, int amount, int field) {
        try {
            return DateUtil.getDateTime(DateUtil.add(DateUtil.parse(date,
                    DateUtil.DEFAULT_DATETIME_FORMAT), amount, field), DateUtil.DEFAULT_DATETIME_FORMAT);
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * 获取系统当前毫秒
     * @return
     */
    public static long getTimeStamp(){
        return System.currentTimeMillis();
    }
    
    /**
     * 指定日期获取系统当前毫秒
     * @param date  当前日期
     * @return
     */
    public static long getTimeStamp(Date date){
        return date.getTime();
    }
    
    /**
     * 指定字符日期与格式获取系统当前毫秒
     * @param dateStr  字符日期
     * @param pattern  日期格式
     * @return 出错时返回系统当前毫秒，未出错则进行正常转换
     */
    public static long getTimeStamp(String dateStr, String pattern){
        if(StringUtils.isEmpty(dateStr)) return System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date == null ? System.currentTimeMillis() : date.getTime();
    }
    

    /**
     * 得到当前年份
     * 
     * @return 当前年份
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 得到当前月份
     * 
     * @return 当前月份
     */
    public static int getCurrentMonth() {
        //用get得到的月份数比实际的小1，需要加上
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }
    
    /**
     * 得到指定时间的月份
     * 
     * @return 
     */
    public static int getCurrentMonth(Date date) {
        //用get得到的月份数比实际的小1，需要加上
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }
    
    /**
     * 得到当前日
     * 
     * @return 当前日
     */
    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DATE);
    }
    
    /**
     * 得到当第几周
     * 
     * @return 当前日
     */
    public static int getCurrentWeekOfYear() {
        return getWeekOfYear(getNow());
    }
    
    /**
     * 得到当第几周
     * 
     * @return 当前日
     */
    public static int getWeekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    
    /**
     * 得到当第几周
     * 
     * @return yyyyWW
     */
    public static String getWeekOfYearFor53(Date date) {

        String year = DateUtil.getDateTime(date, "yyyy");
        String month = DateUtil.getDateTime(date, "MM");
        String weekOfYear = String.format("%02d", DateUtil.getWeekOfYear(date));
        
        if ("12".equals(month) && "01".equals(weekOfYear)) {
            return String.valueOf(Integer.valueOf(year) + 1) + weekOfYear;
            
        } else {
            return year + weekOfYear;
        }
    }
    
    /**
     * 获取当前是周几，
     * 
     * @return 周日 week = 7
     */
    public static int getDayOfWeek() {
        return getDayOfWeek(getNow());
    }
    
    
    
    /**
     * 获取指定日期是周几，
     * 
     * @return 周日 week = 7
     */
    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if(week==1){
            week = 7;
        }else{
            week = week - 1;
        }
        return week;
    }
    
    
    /**
     * 取得当前日期加减指定分钟后的日期
     * 
     * @param minutes 增加的分钟数
     * @return 增加以后的日期
     */
    public static Date addMinutes(int minutes) {
        return add(getNow(), minutes, Calendar.MINUTE);
    }
    
    
    /**
     * 取得当前日期加减指定分钟后的日期
     * 
     * @param date        指定时间
     * @param minutes 增加的分钟数
     * @return 增加以后的日期
     */
    public static Date addMinutes(Date date, int minutes) {
        return add(date, minutes, Calendar.MINUTE);
    }


    /**
     * 取得当前日期以后若干天的日期。如果要得到以前的日期，参数用负数。 例如要得到上星期同一天的日期，参数则为-7
     * 
     * @param days 增加的日期数
     * @return 增加以后的日期
     */
    public static Date addDays(int days) {
        return add(getNow(), days, Calendar.DATE);
    }
    
    /**
     * 取得指定日期以后若干天的日期。如果要得到以前的日期，参数用负数。
     * 
     * @param date 基准日期
     * @param days 增加的日期数
     * @return 增加以后的日期
     */
    public static Date addDays(Date date, int days) {
        return add(date, days, Calendar.DATE);
    }

    /**
     * 取得指定日期以后若干天的日期。如果要得到以前的日期，参数用负数。
     * 
     * @param date 基准日期
     * @param days 增加的日期数
     * @return 增加以后的日期
     * @throws Exception 
     */
    public static String addDays(String date, int days) {
        try {
            return DateUtil.getDateTime(add(DateUtil.parse(date, 
                    DEFAULT_DATETIME_FORMAT), days, Calendar.DATE), DEFAULT_DATETIME_FORMAT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 取得指定日期以后若干天的日期。如果要得到以前的日期，参数用负数(不包含时分秒)。
     *
     * @param date 基准日期
     * @param days 增加的日期数
     * @param
     * @return 增加以后的日期
     * @throws Exception
     */
    public static String addDaysNotInHMS(String date, int days) {
        try {
            return DateUtil.getDateTime(add(DateUtil.parse(date, DEFAULT_DATE_FORMAT), days, Calendar.DATE), DEFAULT_DATE_FORMAT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 取得当前日期以后某月的日期。如果要得到以前月份的日期，参数用负数。
     * 
     * @param months 增加的月份数
     * @return 增加以后的日期
     */
    public static Date addMonths(int months) {
        return add(getNow(), months, Calendar.MONTH);
    }

    /**
     * 取得指定日期以后某月的日期。如果要得到以前月份的日期，参数用负数。 注意，可能不是同一日子，例如2003-1-31加上一个月是2003-2-28
     * 
     * @param date 基准日期
     * @param months 增加的月份数
     * @return 增加以后的日期
     */
    public static Date addMonths(Date date, int months) {
        return add(date, months, Calendar.MONTH);
    }


    /**
     * 计算两个日期相差天数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
     * 
     * @param one 第一个日期数，作为基准
     * @param two 第二个日期数，作为比较
     * @return 两个日期相差天数
     */
    public static long diffDays(Date one, Date two) {
        return (one.getTime() - two.getTime()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 计算两个日期相差秒数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
     *
     * @param one
     *            第一个日期数，作为基准
     * @param two
     *            第二个日期数，作为比较
     * @return 两个日期相差秒数
     */
    public static long diffSecond(Date one, Date two) {
        return diffMilliseconds(one, two) / 1000;
    }

    /**
     * 计算两个日期相差毫秒数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
     *
     * @param one
     *            第一个日期数，作为基准
     * @param two
     *            第二个日期数，作为比较
     * @return 两个日期相差毫秒数
     */
    public static long diffMilliseconds(Date one, Date two) {
        return one.getTime() - two.getTime();
    }
    /**
     * 计算两个日期相差月份数 如果前一个日期小于后一个日期，则返回负数
     * 
     * @param one 第一个日期数，作为基准
     * @param two 第二个日期数，作为比较
     * @return 两个日期相差月份数
     */
    public static int diffMonths(Date one, Date two) {

        Calendar calendar = Calendar.getInstance();

        //得到第一个日期的年分和月份数
        calendar.setTime(one);
        int yearOne = calendar.get(Calendar.YEAR);
        int monthOne = calendar.get(Calendar.MONDAY);

        //得到第二个日期的年份和月份
        calendar.setTime(two);
        int yearTwo = calendar.get(Calendar.YEAR);
        int monthTwo = calendar.get(Calendar.MONDAY);

        return (yearOne - yearTwo) * 12 + (monthOne - monthTwo);
    }
    
    /**
         *  指定当前日期是否在开始和结束日期范围内
     * @param startDate 开始日期 2020-12-20
     * @param endDate 结束日期2020-12-20
     * @return
     */
    public static boolean betwwenAndForDate(String startDate, String endDate) {
        if(getDate().compareTo(startDate) >= 0 && getDate().compareTo(endDate) <= 0) {
            return true;
        }
        return false;
    }
    
    /**
     *  指定当前时间是否在开始和结束时间范围内
     * @param startTime 开始日期 2020-12-20 12:00:00
     * @param endTime 结束日期2020-12-20 12:00:00
     * @return
     */
    public static boolean betwwenAndForTime(String startTime, String endTime) {
        if(getDateTime().compareTo(startTime) >= 0 && getDateTime().compareTo(endTime) <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 将一个字符串用给定的格式转换为日期类型。 <br>
     * 注意：如果返回null，则表示解析失败
     * 
     * @param datestr 需要解析的日期字符串
     * @param pattern 日期字符串的格式，默认为“yyyy-MM-dd”的形式
     * @return 解析后的日期
     */
    public static Date parse(String datestr, String pattern) {
        Date date = null;

        if (null == pattern || "".equals(pattern)) {
            pattern = DEFAULT_DATE_FORMAT;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            date = dateFormat.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
    
    /**
     * 返回当天前后的特定天的开始或结束时间
     * 
     * @param addDay
     * @param endTime   true:指定天的23:59:59、false:指定天的00:00:00
     * @return
     */
    public static Date getDayOfYear(int addDay, boolean endTime) {
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, addDay);
        if (endTime) {
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
        }
        return calendar.getTime();
    }
    
    /**
     * 返回当周前后的特定周的周一或周日
     * 
     * @param addWeek
     * @param endTime   true:指定周的结束、false:指定周的开始
     * @return
     */
    public static Date getWeekOfYear(int addWeek, boolean weekEnd) {
        return getWeekOfYear(DateUtil.getNow(), addWeek, weekEnd);
    }
    
    /**
     * 返回指定日期前后的特定周的周一或周日
     * 
     * @param addWeek
     * @param endTime   true:指定周的结束、false:指定周的开始
     * @return
     */
    public static Date addWeek(Date date, int addWeek) {
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.add(Calendar.WEEK_OF_YEAR, addWeek);
        return calendar.getTime();
    }
    
    /**
     * 返回指定日期前后的特定周的周一或周日
     * 
     * @param addWeek
     * @param endTime   true:指定周的结束、false:指定周的开始
     * @return
     */
    public static Date getWeekOfYear(Date date, int addWeek, boolean weekEnd) {
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.add(Calendar.WEEK_OF_YEAR, addWeek);
        if (weekEnd) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
        } else {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
        }
        return calendar.getTime();
    }
    
    /**
     * 返回当月前后的特定月的第一开或最后一天
     * 
     * @param addMonth
     * @param lastDay   true:最后一天、false:第一天
     * @return
     */
    public static Date getMonth(int addMonth, boolean lastDay) {

        Calendar calendar = Calendar.getInstance();
        if (lastDay) {
            calendar.add(Calendar.MONTH, addMonth + 1);
            calendar.set(Calendar.DAY_OF_MONTH, 0);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
        } else {
            calendar.add(Calendar.MONTH, addMonth);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
        }
        return calendar.getTime();
    }

    /**
     * 返回本月的最后一天
     * 
     * @return 本月最后一天的日期
     */
    public static Date getMonthLastDay() {
        return getMonthLastDay(getNow());
    }

    /**
     * 返回给定日期中的月份中的最后一天
     * 
     * @param date 基准日期
     * @return 该月最后一天的日期
     */
    public static Date getMonthLastDay(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        //将日期设置为下一月第一天
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1, 23, 59, 59);

        //减去1天，得到的即本月的最后一天
        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();
    }

    /**
     * 返回给定日期中的月份的上月第一天
     *
     * @param date 基准日期
     * @return 该日期上月最后一天的日期
     */
    public static Date getUpMonthLastDay(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        //将日期设置为当月第一天
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1, 23, 59, 59);

        //减1天，得到的即上月的最后一天
        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();
    }

    /**
     * 返回给定日期的指定月份前的时间
     *
     * @param date 基准日期
     * @return 该日期上月最后一天的日期
     */
    public static Date getReduceByMonth(Date date ,int month) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 返回给定日期的年份的第一天或最后一天
     *
     * @param date 基准日期
     * @param lastDay   true:最后一天、false:第一天
     * @return 该日期上月最后一天的日期
     */
    public static Date getYearLastDay(Date date,boolean lastDay) {
        if (lastDay){
            // 最后一天
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            //将日期设置为当年最后一天
            calendar.set(calendar.get(Calendar.YEAR), Calendar.DECEMBER, 31, 23, 59, 59);

            return calendar.getTime();
        } else {
            // 第一天
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            //将日期设置为当年第一天
            calendar.set(calendar.get(Calendar.YEAR), Calendar.JANUARY, 1, 23, 59, 59);

            return calendar.getTime();
        }
    }

    
    /**
     * 处理字符串格式的时间
     * @param timeStr
     * @return
     */
    public static String trimAfterPointer(String timeStr){
        String result = "";
        if(!StringUtils.isBlank(timeStr)){
            result = timeStr.contains(".") ? timeStr.substring(0, timeStr.indexOf(".")) : timeStr;
        }
        return result;
    }
    
    /**
     * 将Timestamp格式（yyyy-MM-dd HH:mm:ss）的时间转为String
     * @param time
     * @return
     */
    public static String formatTimestamp(Timestamp time){
        return formatTimestamp(time, DateUtil.DEFAULT_DATETIME_FORMAT);
    }
    
    /**
     * 将Timestamp按指定格式的时间转为String
     * @param time
     * @param pattern
     * @return
     */
    public static String formatTimestamp(Timestamp time, String pattern){
        String result = "";

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time.getTime());
        Date current = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        result = format.format(current);

        return result;
    }
    
    /**
     * 把时间字符串转换成timestamp
     * @param time
     * @return
     */
    public static Timestamp string2Timestamp(String time){
        return Timestamp.valueOf(time);
    }
    
    /**
     * 入库 -- 把页面获取来的日期按对应格式进行入库
     * @param dateVals
     * @param type
     * @return
     */
    public static String parseDate(String dateVals, int type){
        String result = "";
        SimpleDateFormat format;
        SimpleDateFormat daters;
        Date current;
        try {
            switch(type){
            case DATE_TYPE_MONTH:
                format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
                current = format.parse(dateVals);
                daters = new SimpleDateFormat(DEFAULT_MONTH_FORMAT);
                result = daters.format(current);
                break;
            case DATE_TYPE_DEFAULT:
                format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
                current = format.parse(dateVals);
                daters = new SimpleDateFormat(DEFAULT_DATE_FORMAT_SHT);
                result = daters.format(current);
                break;
            case DATE_TYPE_TIMESTAMP:
                format = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
                current = format.parse(dateVals);
                daters = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_SHT);
                result = daters.format(current);
                break;
            case DATE_TYPE_MINTIME:
                format = new SimpleDateFormat(DEFAULT_DATETIME_MINTIME);
                current = format.parse(dateVals);
                daters = new SimpleDateFormat(DEFAULT_DATETIME_MINTIME_SHT);
                result = daters.format(current);
                break;
            case DATE_TYPE_DEFAULT_CH:
                format = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_CH);
                current = format.parse(dateVals);
                daters = new SimpleDateFormat(DEFAULT_DATE_FORMAT_CH);
                result = daters.format(current);
                break;
            default:
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static String changeDate(String dateVals, int type){
        String result = "";
         SimpleDateFormat format;
         SimpleDateFormat daters;
         Date current;
         try {
             if(!StringUtils.isEmpty(dateVals)){
                 format = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
                 current = format.parse(dateVals);
                 daters = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_CH);
                 result = daters.format(current);
             }
         } catch (ParseException e) {
             e.printStackTrace();
         }
         return result;
    }
    
    /**
     * 显示  -- 把库中显示为纯数字格式的日期字符串按对应的显示规则进行显示
     * @param dateVals
     * @param type
     * @return
     */
    public static String generateDate(String dateVals, int type){
        String result = "";
        SimpleDateFormat format;
        SimpleDateFormat daters;
        Date current;
        try {
            if(!StringUtils.isEmpty(dateVals)){
                switch(type){
                case DATE_TYPE_DEFAULT:
                    format = new SimpleDateFormat(DEFAULT_DATE_FORMAT_SHT);
                    current = format.parse(dateVals);
                    daters = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
                    result = daters.format(current);
                    break;
                case DATE_TYPE_TIMESTAMP:
                    format = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_SHT);
                    current = format.parse(dateVals);
                    daters = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
                    result = daters.format(current);
                    break;
                case DATE_TYPE_MINTIME:
                    format = new SimpleDateFormat(DEFAULT_DATETIME_MINTIME_SHT);
                    current = format.parse(dateVals);
                    daters = new SimpleDateFormat(DEFAULT_DATETIME_MINTIME);
                    result = daters.format(current);
                    break;
                case DATE_TYPE_DEFAULT_CH:
                    format = new SimpleDateFormat(DEFAULT_DATE_FORMAT_SHT);
                    current = format.parse(dateVals);
                    daters = new SimpleDateFormat(DEFAULT_DATE_FORMAT_CH);
                    result = daters.format(current);
                    break;
                case DATE_TYPE_TIMESTAMP_CH:
                    format = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_SHT);
                    current = format.parse(dateVals);
                    daters = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_CH);
                    result = daters.format(current);
                    break;
                default:
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
   
    /**
     * 取得指定日期所在一年中的第几周
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getSpecifiedNumForDate(String date){
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            calendar.setTime(df.parse(date));
            calendar.setFirstDayOfWeek(Calendar.FRIDAY);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    
    /**
     * 根据时间字符串，时间类型，定义标准来获取当前时间所在当年的第N周
     * @param dateStr
     * @param type
     * @param normal
     * @return
     */
    public static int getWeekFromDateByType(String dateStr, String type, String normal){
        int week = 0;
        SimpleDateFormat format = new SimpleDateFormat(type);
        Calendar calendar = Calendar.getInstance();
        try {
            if(normal.equals(TIME_FORMAT_CH)){
                calendar.setFirstDayOfWeek(Calendar.MONDAY);
            }
            calendar.setTime(format.parse(dateStr));
            week = calendar.get(Calendar.WEEK_OF_YEAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return week;
    }
    /**
     * 根据日期字符串（yyyyMMdd）计算年龄 
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static String getAge(String dateStr){
        return getAge(dateStr, DEFAULT_DATE_FORMAT_SHT);
    }
    
    /**
     * 根据日期字符串计算年龄 
     * @param dateStr
     * @param pattern（例如：yyyyMMdd）
     * @return
     */
    public static String getAge(String dateStr, String pattern){
        int age = 0;
        try {
            Date birthday = DateUtil.parse(dateStr, pattern);

            Calendar cal = Calendar.getInstance(); 

            if (cal.before(birthday)) {
                return "0";
               // throw new IllegalArgumentException( "The birthDay is before Now.It's unbelievable!"); 
            } 

            int yearNow = cal.get(Calendar.YEAR); 
            int monthNow = cal.get(Calendar.MONTH)+1; 
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); 
            
            cal.setTime(birthday); 
            int yearBirth = cal.get(Calendar.YEAR); 
            int monthBirth = cal.get(Calendar.MONTH)+1; 
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH); 

            age = yearNow - yearBirth; 

            if (monthNow <= monthBirth) { 
                if (monthNow == monthBirth) { 
                    //monthNow==monthBirth 
                    if (dayOfMonthNow <= dayOfMonthBirth) { 
                        age--; 
                    } 
                } else { 
                    //monthNow<monthBirth 
                    age--; 
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return age + "";
    }
    
    /**
     * 日期换时间
     * @param dateStr
     * @return
     */
    public static String changeDateToTime(String dateStr){
        String result = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        SimpleDateFormat timeFormat = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        try {
            if(!StringUtils.isEmpty(dateStr)){
                Date date = dateFormat.parse(dateStr);
                result = timeFormat.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static String getFormatTime(String time){
        return time.substring(0, 4)+"-"+time.substring(4, 6)+"-"+time.substring(6, 8)+" "+
                time.substring(8, 10)+":"+time.substring(10, 12)+":"+time.substring(12, 14);
    }
    
    /**
     * 根据数字类型的日期进行截取
     * @param numDate
     * @return
     */
    public static String splitDateFromNumberDate(String numDate){
        String result = "";
        int length = numDate.length();
        switch(length){
            case 8:
                result = numDate;
                break;
            default:
                result = length > 8 ? numDate.substring(0, 8) : "";
        }
        //与当前时间比较，若比当前时间大，进行清空
        String current = parseDate(getDate(), DATE_TYPE_DEFAULT);
        int cur = Integer.parseInt(current);
        int res = Integer.parseInt(result);
        if(cur < res){
            result = "";
        }
        return result;
    }
    
    /**
     * 根据数字格式的时间进行切割并展示
     * @param numDate
     * @return
     */
    public static String shatterDate(String numDate){
        String result = "";
        String temp = "";
        int length = numDate.length();
        switch(length){
            case 8:
                result = generateDate(numDate, DATE_TYPE_DEFAULT);
                break;
            case 12:
                result = generateDate(numDate, DATE_TYPE_MINTIME);
                break;
            case 14:
                result = generateDate(numDate, DATE_TYPE_TIMESTAMP);
                break;
            default:
                temp = numDate.substring(0, 8);
                result = generateDate(temp, DATE_TYPE_DEFAULT);
        }
        return result;

    }
    
    /**
     * 拼装某一天的开始时间
     * @param day(yyyy-MM-dd)
     * @return day(yyyy-MM-dd) 00:00:00
     */
    public static String getStartDateTimeByDay(String day){
        return day + " " + DAY_START_TIME;
    }
    

    
    /**
     * 拼装某一天的结束时间
     * @param day(yyyy-MM-dd)
     * @return day(yyyy-MM-dd) 23:59:59
     */
    public static String getEndDateTimeByDay(String day){
        return day + " " + DAY_END_TIME;
    }
    
    public static String removeEndPointZero(String dateTime) {
        if (StringUtils.isNotBlank(dateTime)) {
            return dateTime.replace(".0", "");
        }
        return dateTime;
    }

    public static String getTimeGLNZ(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat newSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        sdf.setTimeZone(tz);
        Date date = null;
        String time = "";
        try {
            date = sdf.parse(str);
            time = newSdf.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return time;
    }

/*
    public static String addTime(String s){
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        Calendar cd = Calendar.getInstance();
        try {
            cd.setTime(sdf.parse(s));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //当n=1代表是增加一天
        // cd.add(Calendar.DATE,n);
        cd.add(Calendar.YEAR, 1);//增加一年
        //cd.add(Calendar.DATE, -10);//减10天
        //cd.add(Calendar.MONTH, n);//n=1代表增加一个月
        return sdf.format(cd.getTime());  //format(Date date)方法：将制定的日期对象格式，化为指定格式的字符串（例如：“yyyy-MM-dd”）
    }*/

    /**
     * 将字符串日期加一年
     * @param s ：为时间字符串（例如：“2019-05-30”）
     * @return
     */
    public static String addOneYear(String s){
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        Calendar cd = Calendar.getInstance();
        try {
            cd.setTime(sdf.parse(s));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cd.add(Calendar.YEAR, 1);
        return sdf.format(cd.getTime());
    }






    public static void main(String[] args) {
        String time = "{0} {1}";
        final String format = MessageFormat.format(time,DateUtil.getDate(), DateUtil.getDateTime().substring(11));

        List<Demo> demos = new ArrayList<>();
        String a = "2023-10-{0}";
        for (int i=1;i<=50;i++) {
             String format1 = MessageFormat.format(a, i);
             Demo demo = new Demo();
            demo.setTime(format1);
            demos.add(demo);
        }



        Demo demo = new Demo();



        System.out.println(format);
        System.out.println(DateUtil.getDateTime().substring(11));


        System.out.println(getYearTime());

        final int i = DateUtil.addOneYear("2022-11-19 18:50:50").compareTo(DateUtil.getDateTime());
        System.out.println(i);


    }

    @Data
    public static class Demo {

        private String time;

    }

}