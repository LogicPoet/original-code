package com.cat.annotation.check;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author LZ
 * @date 2020/4/26 15:05
 **/

public class MainTest {

    public static void main(String[] args) {
        Student student=new Student();
        student.setAge(20000);
        student.setName("ashdihdwqidhioa");
        try {
            checkProxy(student);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void test(@Valid Student student){
        System.out.println("方法已经执行");
    }

    /**
     * 模拟代理
     *
     * @return
     */
    public static void checkProxy(Student student) throws IllegalAccessException, NoSuchFieldException {
        // 1、得到要校验类的类型

        // 2、得到被校验类加了校验注解的字段
        Class<? extends Student> aClass = student.getClass();
        // 获取私有成员变量对象
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            // 遍历查询是否有注解
            CatCheck annotation = field.getAnnotation(CatCheck.class);
            if (null != annotation){
                // 参数值为true，禁止访问控制检查
                field.setAccessible(true);
                // 获取字段值
                Object value = field.get(student);
                if (value instanceof String ){
                    // 强转
                    String s=(String) value;
                    // 如果是字符串，获取注解值
                    if (s.length()<annotation.min()||s.length()>annotation.max()){
                        throw new  IllegalArgumentException(annotation.msg());
                    }
                }
            }
        }

        // 3、得到校验规则

        // 4、输出校验结果

        // 5、执行后续
        test(student);
    }

}
