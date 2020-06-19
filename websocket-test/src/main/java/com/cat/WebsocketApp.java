package com.cat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @author LZ
 * @date 2020/5/21 9:20
 **/
@SpringBootApplication
@EnableWebSocket
public class WebsocketApp {
    public static void main(String[] args) {
        SpringApplication.run(WebsocketApp.class,args);
    }
}
