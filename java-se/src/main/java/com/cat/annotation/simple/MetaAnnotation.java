package com.cat.annotation.simple;


import java.lang.annotation.*;

/**
 * <p>Title: MetaAnnotation</p>
 * <p>description:
 * 注解的本质就是一个继承了 Annotation 接口的接口。
 *      public interface Override extends Annotation
 *
 * 一个注解准确意义上来说，只不过是一种特殊的注释而已，如果没有解析它的代码，它可能连注释都不如。</p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/9/21 16:13
 **/
//@Target(ElementType.CONSTRUCTOR)值可以是一个数组
@Target(value={ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.CLASS)
@Documented
@Inherited
public @interface MetaAnnotation {

}
/*
* @Target 用于指明被修饰的注解最终可以作用的目标是谁，
*         也就是指明，你的注解到底是用来修饰方法的？修饰类的？还是用来修饰字段属性的。
*   ElementType.TYPE：只允许被修饰的注解作用在类、接口和枚举上
*   ElementType.FIELD：只允许作用在属性字段上
*   ElementType.METHOD：只允许作用在方法上
*   ElementType.PARAMETER：只允许作用在方法参数上
*   ElementType.CONSTRUCTOR：只允许作用在构造器上
*   ElementType.LOCAL_VARIABLE：只允许作用在本地局部变量上
*   ElementType.ANNOTATION_TYPE：只允许作用在注解上
*   ElementType.PACKAGE：只允许作用在包上
*
* @Retention 用于指明当前注解的生命周期
*   RetentionPolicy.SOURCE：当前注解编译期可见，不会写入 class 文件
*   RetentionPolicy.CLASS：类加载阶段丢弃，会写入 class 文件
*   RetentionPolicy.RUNTIME：永久保存，可以反射获取
*
*
* @Documented 注解修饰的注解，当我们执行 JavaDoc 文档打包时会被保存进 doc 文档，反之将在打包时丢弃。
* @Inherited 注解修饰的注解是具有可继承性的，也就说我们的注解修饰了一个类，
*            而该类的子类将自动继承父类的该注解。
* */

