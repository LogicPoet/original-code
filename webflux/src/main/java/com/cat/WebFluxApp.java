package com.cat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * <p>Title: WebFluxApp</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LZ
 * @date 2020/4/11 16:28
 **/
@SpringBootApplication
@EnableReactiveMongoRepositories
public class WebFluxApp {
    public static void main(String[] args) {
        SpringApplication.run(WebFluxApp.class,args);
    }
}
