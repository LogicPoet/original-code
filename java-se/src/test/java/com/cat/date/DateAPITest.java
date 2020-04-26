package com.cat.date;

import lombok.var;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * java8日期api
 * Date对象有几个严重的问题：
 * 它不能转换时区，除了toGMTString()可以按GMT+0:00输出外，Date总是以当前计算机系统的默认时区为基础进行输出。
 * 此外，我们也很难对日期和时间进行加减，计算两个日期相差多少天，计算某个月第一个星期一的日期等。
 *
 * @author LZ
 * @date 2020/4/23 15:16
 **/
public class DateAPITest {

    @Test
    public void display() {
        // 存储的时候是二进制
        int n = 123400;
        // 123400(在打印的时候实际是int转换成了字符串)<该方法是个同步方法>
        System.out.println(n);
        // 1e208(16进制打印)
        System.out.println(Integer.toHexString(n));
        // 根据不同地区的使用习惯格式化显示
        // $123,400.00
        System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(n));
        // ￥123,400.00
        System.out.println(NumberFormat.getCurrencyInstance(Locale.CHINA).format(n));
    }

    @Test
    public void dateTest() {
        //时间戳是计算从1970年1月1日零点（格林威治时区／GMT+00:00）到现在所经历的秒数

        // 获取当前时间:
        Date date = new Date();
        System.out.println(date.getYear() + 1900); // 必须加上1900
        System.out.println(date.getMonth() + 1); // 0~11，必须加上1
        System.out.println(date.getDate()); // 1~31，不能加1
        // 转换为String:
        System.out.println(date.toString());
        // 转换为GMT时区:
        System.out.println(date.toGMTString());
        // 转换为本地时区:
        System.out.println(date.toLocaleString());
    }

    @Test
    public void simpleDateFormatTest() {
        //它用预定义的字符串表示格式化：
        //yyyy：年    MM：月      dd: 日
        //HH: 小时（24小时） /hh: 小时（12小时）   mm: 分钟    ss: 秒 / SS: 毫秒
        Date date = new Date();
        var sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
    }

    @Test
    public void simpleDateFormat2Test() {
        // E：中文星期    MMM：中文月份
        //一般来说，字母越长，输出越长。以M为例，假设当前月份是9月：
        //M：输出9
        //MM：输出09
        //MMM：输出Sep
        //MMMM：输出September
        Date date = new Date();
        var sdf2 = new SimpleDateFormat("E MMM dd, yyyy");
        System.out.println(sdf2.format(date));
    }

}
