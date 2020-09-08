package com.cat.event.driven.listener;

import com.cat.event.driven.event.NotifyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 实现一个类作为事件的订阅者啦，当事件发布时，会通知订阅者，然后订阅者做相关的处理，
 * 比如新用户注册发送事件自动发送欢迎邮件等等。
 * 同时，Spring 4.2 版本更新的EventListener，可以很方便帮助我们实现事件与方法的绑定，
 * 只需要在目标方法上加上EventListener即可
 *
 *
 * @author LZ
 * @date 2020/8/19 14:26
 **/
@Component
public class NotifyListener {

    /**
     * 当有NotifyEvent 类型的事件发生时，交给sayHello方法处理
     *
     * @param notifyEvent
     */
    @Async
    @EventListener
    public void sayHello(NotifyEvent notifyEvent){
        System.out.println("收到事件:"+notifyEvent.getMsg());
    }

}
