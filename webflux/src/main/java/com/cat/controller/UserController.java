package com.cat.controller;

import com.cat.domain.User;
import com.cat.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.lang.reflect.Method;

/**
 * <p>Title: UserController</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LZ
 * @date 2020/4/11 16:29
 **/
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    /**
     * 获取所有
     *
     * @return
     */
    @GetMapping("/all")
    public Flux<User> getAll(){
        return userRepository.findAll();
    }

    /**
     * 获取所有
     *
     * @return 流方式
     */
    @GetMapping(value = "/stream/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll(){
        return userRepository.findAll();
    }

}
