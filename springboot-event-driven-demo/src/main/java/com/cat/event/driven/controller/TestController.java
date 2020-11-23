package com.cat.event.driven.controller;

import com.cat.event.driven.publisher.NotifyPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LZ
 * @date 2020/8/19 14:28
 **/
@RestController
public class TestController {

    @Autowired
    private NotifyPublisher notifyPublisher;

    @GetMapping("/sayHello")
    public String sayHello(){
        notifyPublisher.publishEvent(1, "我发布了一个事件");
        return "Hello Word";
    }

}
