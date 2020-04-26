package com.cat.date;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Instant表示高精度时间戳，它可以和ZonedDateTime以及long互相转换。
 *  ┌──── ──┐
 * │LocalDateTime│────┐
 * └──────┘        │      ┌── ────┐
 *                       ├───>│ZonedDateTime│
 * ┌───────┐    │       └──────┘
 * │   ZoneId    │──┘           ▲
 * └──────┘       ┌────┴─────┐
 *                      │                   │
 *                      ▼                   ▼
 *                ┌───────┐        ┌───────┐
 *               │   Instant   │<───>│    long     │
 *               └──────┘         └──────┘
 *
 * @author LZ
 * @date 2020/4/26 14:04
 **/
public class InstantTest {

    @Test
    public void simpleTest(){
        // 获取当前时间戳
        Instant now = Instant.now();
        System.out.println(now.getEpochSecond()); // 秒
        System.out.println(now.toEpochMilli()); // 毫秒
    }

    @Test
    public void convertTimeAccordingToTimestamp(){
        // 以指定时间戳创建Instant:
        Instant ins = Instant.ofEpochSecond(1568568760);
        ZonedDateTime zdt = ins.atZone(ZoneId.systemDefault());
        System.out.println(zdt); // 2019-09-16T01:32:40+08:00[Asia/Shanghai]
    }

}
