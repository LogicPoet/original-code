package com.cat.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author LZ
 * @date 2020/4/26 10:42
 **/
public class ZonedDateTimeTest {

    @Test
    public void simpleTest() {
        ZonedDateTime zbj = ZonedDateTime.now(); //默认时区
        ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York"));//用指定时区获取当前时间

        System.out.println(zbj);
        System.out.println(zny);
    }

    @Test
    public void simple2Test() {
        LocalDateTime ldt = LocalDateTime.of(2020, 2, 2, 20, 20, 20);

        ZonedDateTime zbj = ldt.atZone(ZoneId.systemDefault());
        ZonedDateTime zny = ldt.atZone(ZoneId.of("America/New_York"));

        System.out.println(zbj);
        System.out.println(zny);
    }

    @Test
    public void timeZoneConversion() {
        // 以中国时区获取当前时间
        ZonedDateTime zbj = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        // 转换为纽约时间
        ZonedDateTime zny = zbj.withZoneSameInstant(ZoneId.of("America/New_York"));

        System.out.println(zbj);
        System.out.println(zny);
    }

    @Test
    public void airplaneSchedule() {
        LocalDateTime departureAtBeijing = LocalDateTime.of(2019, 9, 15, 13, 0, 0);
        int hours = 13;
        int minutes = 20;
        LocalDateTime arrivalAtNewYork = calculateArrivalAtNY(departureAtBeijing, hours, minutes);
        System.out.println(departureAtBeijing + " -> " + arrivalAtNewYork);
        // test:
        if (!LocalDateTime.of(2019, 10, 15, 14, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 10, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        } else if (!LocalDateTime.of(2019, 11, 15, 13, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 11, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        }
    }

    static LocalDateTime calculateArrivalAtNY(LocalDateTime bj, int h, int m) {
        // 时刻加上飞机飞行时间
        LocalDateTime bjToNY = bj.plusHours(h).plusMinutes(m);
        // 设置北京时区
        ZonedDateTime zdt = bjToNY.atZone(ZoneId.of("Asia/Shanghai"));
        // 转换纽约时区
        ZonedDateTime zny = zdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        return zny.toLocalDateTime();
    }

}
