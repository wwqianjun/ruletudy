package com.qianjun.rules.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间服务类 基于joda 类DateTimeUtil.java的实现描述：TODO 类实现描述
 *
 */
public class DateTimeUtil {
    public final static String  FORMATE_DATE    = "yyyy-MM-dd";
    public final static String  FORMATE_SECONDS = "HH:mm:ss";
    public final static String  FORMATE_FULL    = FORMATE_DATE.concat(" ").concat(FORMATE_SECONDS);
    private final static Logger LOG             = LoggerFactory.getLogger(DateTimeUtil.class);

    public final static String  PLUS_HOURS      = "1";
    public final static String  PLUS_DAYS       = "2";
    public final static String  PLUS_WEEKS      = "3";
    public final static String  PLUS_MONTHS     = "4";
    public final static String  PLUS_YEARS      = "5";
    public final static String  MINUS_HOURS     = "6";
    public final static String  MINUS_DAYS      = "7";
    public final static String  MINUS_WEEKS     = "8";
    public final static String  MINUS_MONTHS    = "9";
    public final static String  MINUS_YEARS     = "10";
    public final static String  PLUS_MINS       = "11";
    public final static String  MINUS_MINS      = "12";

    /**
     * 验证 时间 是否在开始和结束
     * @param strat
     * @param end
     * @param time
     * @return
     */
    public static boolean isBetween(Date strat, Date end, Date time) {

        DateTime beginTime = new DateTime(strat);
        DateTime endTime = new DateTime(end);
        DateTime now = DateTime.now();

        if (time != null) {
            now = new DateTime(time);
        }

        Interval interval = new Interval(beginTime, endTime);
        return interval.contains(now);
    }

    /**
     * 默认年月日
     * 
     * @param formatStr
     * @return
     */
    public static String getToday(String formatStr) {

        return getDayOfYear(0, formatStr);
    }

    /**
     * 默认年月日
     * 
     * @param formatStr
     * @return
     */
    public static String getYesterday(String formatStr) {

        return getDayOfYear(-1, formatStr);
    }

    /**
     * 默认年月日
     * 
     * @param formatStr
     * @return
     */
    public static String getTomorrow(String formatStr) {

        return getDayOfYear(1, formatStr);
    }

    /**
     * 默认年月日时分秒
     * 
     * @param formatStr
     * @return
     */
    public static String getCurrentDate(String formatStr) {
        if (StringUtils.isBlank(formatStr)) {
            formatStr = FORMATE_FULL;
        }
        return getDayOfYear(0, formatStr);
    }

    /**
     * day 等于0今天，等于1 明天，等于-1 昨天
     * 
     * @param day
     * @param formatStr
     * @return
     */
    public static String getDayOfYear(int day, String formatStr) {
        if (StringUtils.isBlank(formatStr)) {
            formatStr = FORMATE_DATE;
        }
        return DateTime.now().dayOfYear().addToCopy(day).toString(formatStr);
    }

    public static Date dateFormat(String formatStr, final String date) {
        Date result = null;
        if (StringUtils.isBlank(date)) {
            return null;
        }
        if (StringUtils.isBlank(formatStr)) {
            formatStr = FORMATE_DATE;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            result = format.parse(date);
        } catch (ParseException e) {
            LOG.error("格式化时间错误", e);
        }
        return result;
    }

    /**
     * Date类型时间格式化
     * 
     * @param formatStr
     * @param date
     * @return
     */
    public static String dateFormat(String formatStr, final Date date) {
        String result = null;
        if (null == date) {
            return null;
        }
        if (StringUtils.isBlank(formatStr)) {
            formatStr = FORMATE_DATE;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        result = format.format(date);
        return result;
    }

    /**
     * 加小时
     * 
     * @param formatStr
     * @param time
     * @param number
     * @return
     */
    public static String plusHours(String formatStr, Date time, int number) {
        return dateOperate(formatStr, time, PLUS_HOURS, number);
    }

    /**
     * 时间加减 默认加小时
     * 
     * @param formatStr
     * @param time
     * @param type
     * @param number
     * @return
     */
    public static String dateOperate(String formatStr, Date time, String type, int number) {
        if (StringUtils.isBlank(formatStr))
            formatStr = FORMATE_FULL;
        DateTime dateTime = new DateTime(time);
        DateTime resultTime;
        switch (type) {
            case "1":
                resultTime = dateTime.plusHours(number);
                break;
            case "2":
                resultTime = dateTime.plusDays(number);
                break;
            case "3":
                resultTime = dateTime.plusWeeks(number);
                break;
            case "4":
                resultTime = dateTime.plusMonths(number);
                break;
            case "5":
                resultTime = dateTime.plusYears(number);
                break;
            case "6":
                resultTime = dateTime.minusHours(number);
                break;
            case "7":
                resultTime = dateTime.minusDays(number);
                break;
            case "8":
                resultTime = dateTime.minusWeeks(number);
                break;
            case "9":
                resultTime = dateTime.minusMonths(number);
                break;
            case "10":
                resultTime = dateTime.minusYears(number);
                break;
            case "11":
                resultTime = dateTime.plusMinutes(number);
                break;
            case "12":
                resultTime = dateTime.minusMinutes(number);
                break;
            default:
                resultTime = dateTime.plusHours(number);
                break;
        }
        return resultTime.toString(formatStr);
    }

    /**
     * 小时转分钟
     * 
     * @param hour
     * @return
     */
    public static Long getHourToMillisecond(Integer hour) {
        Long millisecond = 0L;
        if (null != hour) {
            millisecond = Long.valueOf(hour * 60 * 1000);
        }
        return millisecond;
    }

    /**
     * 天数加减
     */
    public static Date dateOp(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //		c.set(Calendar.DATE, c.get(Calendar.DATE) +i);
        c.add(Calendar.DATE, i);
        return c.getTime();
    }

    /**
     * 小时加减
     * 
     * @param date
     * @param i
     * @return
     */
    public static Date dateOpForHour(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, i);
        return c.getTime();
    }

    /**
     * 分钟加减
     * 
     * @param date
     * @param i
     * @return
     */
    public static Date dateOpForMinute(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, i);
        return c.getTime();
    }

    /**
     * 减分钟
     * 
     * @param formatStr
     * @param time
     * @param number
     * @return
     */
    public static String minusMinutes(String formatStr, Date time, int number) {
        return dateOperate(formatStr, time, MINUS_MINS, number);
    }
  
}
