package com.cat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LZ
 * @date 2020/5/21 9:49
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "成功";
    }
}
