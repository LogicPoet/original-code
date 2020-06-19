package com.cat.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LZ
 * @date 2020/6/19 9:48
 **/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

}
