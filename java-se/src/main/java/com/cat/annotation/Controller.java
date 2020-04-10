package com.cat.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: Controller</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/9/23 20:34
 **/
@Target(ElementType.TYPE)//ElementType.TYPE：只允许被修饰的注解作用在类、接口和枚举上
@Retention(RetentionPolicy.RUNTIME)//RetentionPolicy.RUNTIME：永久保存，可以反射获取
public @interface Controller {
}
