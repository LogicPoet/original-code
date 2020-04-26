package com.cat.date;

import lombok.var;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Calendar可以用于获取并设置年、月、日、时、分、秒，
 * 它和Date比，主要多了一个可以做简单的日期和时间运算的功能。
 *
 * @author LZ
 * @date 2020/4/23 16:09
 **/
public class CalendarAPITest {

    @Test
    public void simpleTest() {
        // 获取当前时间:
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        //月份要+1
        int m = 1 + c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);
        //周日=1，周六=7
        int w = c.get(Calendar.DAY_OF_WEEK);
        int hh = c.get(Calendar.HOUR_OF_DAY);
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);
        //毫秒
        int ms = c.get(Calendar.MILLISECOND);
        System.out.println(y + "-" + m + "-" + d + " " + w + " " + hh + ":" + mm + ":" + ss + "." + ms);
    }

    @Test
    public void setASpecificTimeTest() {
        // Calendar只有一种方式获取，即Calendar.getInstance()，而且一获取到就是当前时间。
        // 如果我们想给它设置成特定的一个日期和时间，就必须先清除所有字段：

        // 当前时间:
        Calendar c = Calendar.getInstance();
        // 清除所有:
        c.clear();
        // 设置2019年:
        c.set(Calendar.YEAR, 2019);
        // 设置9月:注意8表示9月:
        c.set(Calendar.MONTH, 8);
        // 设置2日:
        c.set(Calendar.DATE, 2);
        // 设置时间:
        c.set(Calendar.HOUR_OF_DAY, 21);
        c.set(Calendar.MINUTE, 22);
        c.set(Calendar.SECOND, 23);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime()));
        // 2019-09-02 21:22:23
    }

    @Test
    public void timeZoneTest() {
        TimeZone tzDefault = TimeZone.getDefault(); // 当前时区
        TimeZone tzGMT9 = TimeZone.getTimeZone("GMT+09:00"); // GMT+9:00时区
        TimeZone tzNY = TimeZone.getTimeZone("America/New_York"); // 纽约时区
        System.out.println(tzDefault.getID()); // Asia/Shanghai
        System.out.println(tzGMT9.getID()); // GMT+09:00
        System.out.println(tzNY.getID()); // America/New_York
        //所有可用的时区id
        for (String availableID : TimeZone.getAvailableIDs()) {
            System.out.println(availableID);
        }
    }

    /**
     * 可见，利用Calendar进行时区转换的步骤是：
     *
     * 1、清除所有字段；
     * 2、设定指定时区；
     * 3、设定日期和时间；
     * 4、创建SimpleDateFormat并设定目标时区；
     * 5、格式化获取的Date对象（注意Date对象无时区信息，时区信息存储在SimpleDateFormat中）。
     * ps：因此，本质上时区转换只能通过SimpleDateFormat在显示的时候完成。
     */
    @Test
    public void convertTimeZoneTest() {
        // 当前时间:
        Calendar c = Calendar.getInstance();
        // 清除所有:
        c.clear();
        // 设置为北京时区:
        c.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        // 设置年月日时分秒:
        c.set(2019,10 /* 11月 */,20,8,15,0);
        // 显示时间:
        var sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        System.out.println(sdf.format(c.getTime()));
        // 2019-11-19 19:15:00
    }

    @Test
    public void calculatingTimeTest() {
        // 当前时间:
        Calendar c = Calendar.getInstance();
        // 清除所有:
        c.clear();
        // 设置年月日时分秒:
        c.set(2019, 10 /* 11月 */, 20, 8, 15, 0);
        // 加5天并减去2小时:
        c.add(Calendar.DAY_OF_MONTH, 5);
        c.add(Calendar.HOUR_OF_DAY, -2);
        // 显示时间:
        var sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = c.getTime();
        System.out.println(sdf.format(d));
        // 2019-11-25 6:15:00
    }


}
