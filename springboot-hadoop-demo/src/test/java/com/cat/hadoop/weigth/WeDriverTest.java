package com.cat.hadoop.weigth;

import com.cat.hadoop.HadoopAppTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class WeDriverTest extends HadoopAppTest {

    @Autowired
    private WeDriver weDriver;

    @Test
    public void jobStart() throws InterruptedException, IOException, ClassNotFoundException {
        weDriver.jobStart();
    }
}
