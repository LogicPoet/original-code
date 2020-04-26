package com.cat.date;

import lombok.var;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 使用旧的Date对象时，我们用SimpleDateFormat进行格式化显示。
 * 使用新的LocalDateTime或ZonedLocalDateTime时，我们要进行格式化显示，就要使用DateTimeFormatter。
 *
 * 和SimpleDateFormat不同的是，DateTimeFormatter不但是不变对象，它还是线程安全的。
 * 线程的概念我们会在后面涉及到。现在我们只需要记住：
 *      因为SimpleDateFormat不是线程安全的，使用的时候，只能在方法内部创建新的局部变量。
 * 而DateTimeFormatter可以只创建一个实例，到处引用。
 *
 * @author LZ
 * @date 2020/4/26 12:05
 **/
public class DateTimeFormatterTest {

    @Test
    public void simpleTest(){
        ZonedDateTime zdt = ZonedDateTime.now();
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm ZZZZ");
        System.out.println(formatter.format(zdt));

        var zhFormatter = DateTimeFormatter.ofPattern("yyyy MMM dd EE HH:mm", Locale.CHINA);
        System.out.println(zhFormatter.format(zdt));

        var usFormatter = DateTimeFormatter.ofPattern("E, MMMM/dd/yyyy HH:mm", Locale.US);
        System.out.println(usFormatter.format(zdt));
    }

}
