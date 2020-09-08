package com.cat.event.driven.publisher;

import com.cat.event.driven.event.NotifyEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 新建一个NotifyPublisher用于我们事件的发布工作，该类实现了ApplicationContextAware并重写了setApplicationContext 方法
 * 这一步的目的是可以获取我们Spring的应用上下文，因为事件的发布是需要应用上下文来做的
 *
 * @author LZ
 * @date 2020/8/19 14:20
 **/
@Component
public class NotifyPublisher implements ApplicationContextAware {
    /**
     * 应用上下文
     */
    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    /**
     * 发布一个消息，这里大家可以根据不同的状态实现发布不同的事件
     *
     * @param status
     * @param msg
     */
    public void publishEvent(int status, String msg) {
        if (status == 0) {
            ctx.publishEvent(new NotifyEvent(this, msg));
        } else {
            ctx.publishEvent(new NotifyEvent(this,msg)) ;
        }
    }

}
