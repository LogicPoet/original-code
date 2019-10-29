package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: RequestMapping</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/9/23 20:36
 **/
@Target({ElementType.TYPE, ElementType.METHOD})//ElementType.METHOD：只允许作用在方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value() default "";
}
