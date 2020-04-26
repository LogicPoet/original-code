package com.cat.date;

import lombok.var;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

/**
 * 从Java 8开始，java.time包提供了新的日期和时间API，主要涉及的类型有：
 *
 * 本地日期和时间：LocalDateTime，LocalDate，LocalTime；
 * 带时区的日期和时间：ZonedDateTime；
 * 时刻：Instant；
 * 时区：ZoneId，ZoneOffset；
 * 时间间隔：Duration。
 *
 * 以及一套新的用于取代SimpleDateFormat的格式化类型DateTimeFormatter。
 *
 * 和旧的API相比，新API严格区分了时刻、本地日期、本地时间和带时区的日期时间，并且，对日期和时间进行运算更加方便。
 *
 * 此外，新API修正了旧API不合理的常量设计：
 *
 * Month的范围用1~12表示1月到12月；
 * Week的范围用1~7表示周一到周日。
 * 最后，新API的类型几乎全部是不变类型（和String类似），可以放心使用不必担心被修改。
 *
 * @author LZ
 * @date 2020/4/23 16:36
 **/
public class LocalDateTimeTest {

    /**
     * 注意ISO 8601规定的日期和时间分隔符是T。标准格式如下：
     *
     * 日期：yyyy-MM-dd
     * 时间：HH:mm:ss
     * 带毫秒的时间：HH:mm:ss.SSS
     * 日期和时间：yyyy-MM-dd'T'HH:mm:ss
     * 带毫秒的日期和时间：yyyy-MM-dd'T'HH:mm:ss.SSS
     */
    @Test
    public void simpleTest() {
        LocalDate d = LocalDate.now(); // 当前日期
        LocalTime t = LocalTime.now(); // 当前时间
        LocalDateTime dt = LocalDateTime.now(); // 当前日期和时间
        System.out.println(d); // 严格按照ISO 8601格式打印
        System.out.println(t); // 严格按照ISO 8601格式打印
        System.out.println(dt); // 严格按照ISO 8601格式打印
    }

    @Test
    public void simple2Test() {
        // 指定日期和时间:
        // 如果指定的时间不存在会报DateTimeException
        LocalDate d2 = LocalDate.of(2019, 11, 30); // 2019-11-30, 注意11=11月
        LocalTime t2 = LocalTime.of(15, 16, 17); // 15:16:17
        LocalDateTime dt2 = LocalDateTime.of(2019, 11, 30, 15, 16, 17);
        LocalDateTime dt3 = LocalDateTime.of(d2, t2);
        System.out.println(d2);
        System.out.println(t2);
        System.out.println(dt2);
        System.out.println(dt3);
    }

    @Test
    public void simple3Test() {
        // 因为严格按照ISO 8601的格式，因此，将字符串转换为LocalDateTime就可以传入标准格式：
        // 如果指定日期不存在会报DateTimeParseException
        LocalDateTime dt4 = LocalDateTime.parse("2019-11-19T15:16:17");
        LocalDate d4 = LocalDate.parse("2019-11-19");
        LocalTime t4 = LocalTime.parse("15:16:17");
        System.out.println(dt4);
        System.out.println(d4);
        System.out.println(t4);
    }

    @Test
    public void dateTimeFormatterTest() {
        // 自定义格式化:
        var dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()));

        // 用自定义格式解析:
        LocalDateTime dt2 = LocalDateTime.parse("2019/11/30 15:16:17", dtf);
        System.out.println(dt2);
    }

    @Test
    public void calculatingTimeTest() {
        LocalDateTime dt = LocalDateTime.of(2019, 10, 26, 20, 30, 59);
        System.out.println(dt);
        // 加5天减3小时:
        LocalDateTime dt2 = dt.plusDays(5).minusHours(3);
        System.out.println(dt2); // 2019-10-31T17:30:59
        // 减1月:
        LocalDateTime dt3 = dt2.minusMonths(1);
        System.out.println(dt3); // 2019-09-30T17:30:59
    }

    /**
     * 对日期和时间进行调整则使用withXxx()方法，例如：withHour(15)会把10:11:12变为15:11:12：
     *
     * 调整年：withYear()
     * 调整月：withMonth()
     * 调整日：withDayOfMonth()
     * 调整时：withHour()
     * 调整分：withMinute()
     * 调整秒：withSecond()
     */
    @Test
    public void withXxxTest() {
        LocalDateTime dt = LocalDateTime.of(2019, 10, 26, 20, 30, 59);
        System.out.println(dt);
        // 日期变为31日:<如果调整的日期不存在会报 DateTimeException >
        LocalDateTime dt2 = dt.withDayOfMonth(31);
        System.out.println(dt2); // 2019-10-31T20:30:59
        // 月份变为9:
        LocalDateTime dt3 = dt2.withMonth(9);
        System.out.println(dt3); // 2019-09-30T20:30:59
    }

    @Test
    public void complexOperationsTest() {
        // 本月第一天0:00时刻:
        LocalDateTime firstDay = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        System.out.println(firstDay);

        // 本月最后1天:
        LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);

        // 下月第1天:
        LocalDate nextMonthFirstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println(nextMonthFirstDay);

        // 本月第1个周一:
        LocalDate firstWeekday = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println(firstWeekday);

        // 本月第一个工作日
        //for (int i = 0; i < 5; i++) {
        //    LocalDate with2 = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.of(i)));
        //    LocalDate with1 = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY));
        //}
        //System.out.println(with1);
        //System.out.println(with2);

    }

    @Test
    public void compareDatesTest(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        System.out.println(now.isBefore(target));
        System.out.println(LocalDate.now().isBefore(LocalDate.of(2019, 11, 19)));
        System.out.println(LocalTime.now().isAfter(LocalTime.parse("08:15:00")));
    }

    @Test
    public void timeInterval(){
        LocalDateTime start = LocalDateTime.of(2019,11,19,20,30,12);
        LocalDateTime end = LocalDateTime.of(2020,11,19,20,30,12);

        Duration between = Duration.between(start, end);
        System.out.println(between);


        Period until = LocalDate.of(2019, 8, 12).until(LocalDate.of(2020, 4, 8));
        System.out.println(until);
    }

}
