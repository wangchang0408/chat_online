package com.maple.chat.utils;

import android.util.Log;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * @author wangchang
 * @date 2020-5-23 16:24
 */
public class TimeUtils {
    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    public static final SimpleDateFormat format1 = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.CHINA);
    public static final SimpleDateFormat format2 = new SimpleDateFormat("MM-dd", Locale.CHINA);
    public static final SimpleDateFormat format3 = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
    public static final SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    public static final SimpleDateFormat format5 = new SimpleDateFormat("HH:mm", Locale.CHINA);
    public static final SimpleDateFormat format6 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

    /**
     * 获取当前时间字符串格式化
     *
     * @return
     */
    public static String getNowTime() {
        Date date = new Date();
        String time = format.format(date);
        return time;
    }

    /**
     * 获取当前年月日 时分秒 自定义
     **/
    public static String getNowTimeStr() {
        Date date = new Date();
        String time = format4.format(date);
        return time;
    }

    /**
     * 获取当前年月日 自定义
     **/
    public static String getNowDate() {
        Date date = new Date();
        String time = format6.format(date);
        return time;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Timestamp getNowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 转换时间为字符串
     *
     * @param time
     * @return
     */
    public static String parse2Str(Timestamp time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        return format.format(time);
    }

    public static String parse2Str(Date time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        return format.format(time);
    }


    public static String parse4Str(Date time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        return format4.format(time);
    }



    public static String parse2Str1(Timestamp time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        return format1.format(time);
    }

    public static String parse2Str2(Timestamp time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        return format2.format(time);
    }

    public static String parse2Str3(Timestamp time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        return format3.format(time);
    }

    public static String parse2Str3(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        try {
            Date parse = format.parse(time);
            return format3.format(parse);
        } catch (ParseException e) {
            Log.e("1", "parse2Str3时间转换出错");
            return "";
        }
    }


    public static String parseStr4(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        try {
            Date parse = format4.parse(time);
            return format3.format(parse);
        } catch (ParseException e) {
            Log.e("1", "parseStr4时间转换出错");
            return "";
        }
    }


    public static String parse2Str4(Timestamp time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        return format4.format(time);
    }

    public static String parse2Str4(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        try {
            Date parse = format.parse(time);
            return format4.format(parse);
        } catch (ParseException e) {
            Log.e("1", "parse2Str4时间转换出错");
            return "";
        }
    }

    public static String parse2Str5(Timestamp time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        return format5.format(time);
    }


    /**
     * 字符串转换为时间
     *
     * @param str
     * @return
     */
    public static Timestamp parse2Timestamp(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Timestamp.valueOf(str);
        } catch (Exception e) {
            Log.e("1", "parse2Timestamp时间转换出错");
        }
        return null;
    }

    /**
     * 字符串转换为时间
     *
     * @param str
     * @return
     */
    public static Date parse2Date(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return format.parse(str);
        } catch (ParseException e) {
            Log.e("1", "parse2Date时间转换出错");
        }
        return null;
    }

    /**
     * 计算响应时间差值， 无所谓前后顺序
     *
     * @param t1
     * @param t2
     * @return
     */
    public static String respTime(Timestamp t1, Timestamp t2) {
        String info = "";
        try {
            Long respTime = t2.getTime() - t1.getTime();
            info += Math.abs(respTime);
            info += "毫秒 ";
            info += Math.abs(respTime / 1000.0);
            info += "秒";
        } catch (Exception e) {
            Log.e("1", "respTime时间转换出错");
        }
        return info;
    }

    /**
     * 计算早晚点的时间展示
     * 晚点时间 展示为+00 -00分
     */
    public static String soonerOrLater(Timestamp realTime, Timestamp planTime) {
        StringBuffer result = new StringBuffer();
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long diffMark;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        if (null == realTime || null == planTime) {
            return "";
        }
        result.append(parse2Str5(planTime));
        result.append(" ");
        diffMark = realTime.getTime() - planTime.getTime();
        diff = Math.abs(realTime.getTime() - planTime.getTime());
        min = diff % nd % nh / nm + day * 24 * 60;
        if (min > 0) {
            if (diffMark > 0) {
                result.append("晚");
            } else {
                result.append("早");
            }
            result.append(min);
            result.append("′");
            return result.toString();
        }

        return "";
    }

    /**
     * 处理消息的时间
     * 当天显示时分
     * 昨天显示昨天 时分
     * 前天显示 年月日 时分
     *
     * @param time
     * @return
     */
    public static String delMsgTime(Long time) {
        StringBuffer r = new StringBuffer();
        long curTimeMillis = System.currentTimeMillis();
        Date curDate = new Date(curTimeMillis);
        int todayHoursSeconds = curDate.getHours() * 60 * 60;
        int todayMinutesSeconds = curDate.getMinutes() * 60;
        int todaySeconds = curDate.getSeconds();
        int todayMillis = (todayHoursSeconds + todayMinutesSeconds + todaySeconds) * 1000;
        long todayStartMillis = curTimeMillis - todayMillis;
        int oneDayMillis = 24 * 60 * 60 * 1000;
        long yesterdayStartMilis = todayStartMillis - oneDayMillis;
        long yesterdayBeforeStartMilis = yesterdayStartMilis - oneDayMillis;
        if (time >= todayStartMillis) {
            r.append("");
        } else if (time >= yesterdayStartMilis) {
            r.append("昨天");
        } else {
            r.append(format6.format(new Date(time)));
        }
        r.append(" " + format5.format(new Date(time)));
        return r.toString();
    }
}
