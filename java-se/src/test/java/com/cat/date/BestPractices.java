package com.cat.date;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 *
 * @author LZ
 * @date 2020/4/26 14:19
 **/
public class BestPractices {

    /**
     * 如果要把旧式的Date或Calendar转换为新API对象，可以通过toInstant()方法转换为Instant对象，再继续转换为ZonedDateTime：
     */
    @Test
    public void oldAPIToNewAPI() {
        // Date -> Instant:
        Instant ins1 = new Date().toInstant();

        // Calendar -> Instant -> ZonedDateTime:
        Calendar calendar = Calendar.getInstance();
        Instant ins2 = Calendar.getInstance().toInstant();
        ZonedDateTime zdt = ins2.atZone(calendar.getTimeZone().toZoneId());
        System.out.println(zdt);
    }

    /**
     * 如果要把新的ZonedDateTime转换为旧的API对象，只能借助long型时间戳做一个“中转”：
     */
    @Test
    public void newAPIToOldAPI() {
        // ZonedDateTime -> long:
        ZonedDateTime zdt = ZonedDateTime.now();
        long ts = zdt.toEpochSecond() * 1000;

        // long -> Date:
        Date date = new Date(ts);

        // long -> Calendar:
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeZone(TimeZone.getTimeZone(zdt.getZone().getId()));
        calendar.setTimeInMillis(zdt.toEpochSecond() * 1000);
    }

    /**
     * 数据库	        对应Java类（旧）	        对应Java类（新）
     * DATETIME     	java.util.Date	            LocalDateTime
     * DATE	            java.sql.Date	            LocalDate
     * TIME	            java.sql.Time	            LocalTime
     * TIMESTAMP	    java.sql.Timestamp	        LocalDateTime
     *
     * 实际上，在数据库中，我们需要存储的最常用的是时刻（Instant），因为有了时刻信息，就可以根据用户自己选择的时区，显示出正确的本地时间。
     * 所以，最好的方法是直接用长整数long表示，在数据库中存储为BIGINT类型。
     *
     * 通过存储一个long型时间戳，我们可以编写一个timestampToString()的方法，非常简单地为不同用户以不同的偏好来显示不同的本地时间：
     */
    @Test
    public void timestampConversionTime(){
        long ts = 1574208900000L;
        System.out.println(timestampToString(ts, Locale.CHINA, "Asia/Shanghai"));
        System.out.println(timestampToString(ts, Locale.US, "America/New_York"));
    }

    static String timestampToString(long epochMilli, Locale lo, String zoneId) {
        Instant ins = Instant.ofEpochMilli(epochMilli);
        DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
        return f.withLocale(lo).format(ZonedDateTime.ofInstant(ins, ZoneId.of(zoneId)));
    }
}
