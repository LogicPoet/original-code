package com.cat.hadoop;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author LZ
 * @date 2020/8/25 11:30
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Test{
    @org.junit.Test
    public void test01(){

        log.info("info");

        log.debug("debug");
        log.error("error");
    }
}
