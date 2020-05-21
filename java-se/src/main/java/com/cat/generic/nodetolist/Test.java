package com.cat.generic.nodetolist;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

/**
 * @author LZ
 * @date 2020/4/29 15:04
 **/
public class Test {
    public static void main(String[] args) {
        Student student =new Student();
        student.setId(1);
        cc(student);
    }

    private static<T> void cc(T student) {
        Class<?> aClass = student.getClass();
        System.out.println(aClass.getTypeName());
        Constructor<?>[] constructors = aClass.getConstructors();
        Object o=new Object();
        try {
             o = constructors[0].newInstance();
             o=student;
        } catch (InstantiationException|IllegalAccessException |InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(o);
    }
}
