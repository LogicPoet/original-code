package com.cat.annotation.check;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数校验主键
 *
 * @author LZ
 * @date 2020/4/26 14:57
 **/
@Target(ElementType.FIELD)//只作用在属性字段上
@Retention(RetentionPolicy.RUNTIME)
public @interface CatCheck {

    /**
     * 校验不通过的提示语
     *
     * @return
     */
    String msg() default "";

    /**
     * 校验条件
     *
     * @return
     */
    String condition() default "";
    int min() default 0;

    int max() default 300;

    /**
     * 正则表达式
     *
     * @return
     */
    String regular() default "";
}
