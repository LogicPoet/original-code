package com.cat.event.driven.event;

import org.springframework.context.ApplicationEvent;

/**
 *
 * 新建一个类NotifyEvent 继承ApplicationEvent，用于封装我们事件额外的信息
 * 这里则是String类型的msg，用于记录详细的事件内容。
 *
 * ApplicationEvent 是一个抽象类，扩展了java本身的EventObject 类，
 * 每一个继承了ApplicationEvent的子类都表示一类事件，可以携带数据
 *
 * @author LZ
 * @date 2020/8/19 14:16
 **/
public class NotifyEvent extends ApplicationEvent {

    /**
     * 用于记录详细的事件内容
     */
    private String msg;

    /**
     * 创建一个新的ApplicationEvent
     *
     * @param source 最初发生事件或与事件相关联的对象（永远不为null）
     *
     */
    public NotifyEvent(Object source,String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
