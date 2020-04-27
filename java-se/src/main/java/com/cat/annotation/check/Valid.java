package com.cat.annotation.check;

import java.lang.annotation.*;

/**
 * @author LZ
 * @date 2020/4/26 15:07
 **/
//@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Valid {
}
