package com.cat.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * <p>Title: GetClassInfo</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/9/21 9:06
 **/
public class GetClassInfo {
    public static void main(String[] args) {
        //通过反射获取类的所有变量
        //printFields();

        //通过反射获取类的所有方法
        //printMethods();
    }

    /**
     * 通过反射获取类的所有变量
     */
    private static void printFields() {
        //1.获取并输出类的名称
        Class myClass = SonClass.class;
        System.out.println("类全限定名：" + myClass.getName());
        System.out.println("=============================");

        //2.1 获取所有 public 访问权限的变量
        //包括本类声明的和从父类继承的
        Field[] fields = myClass.getFields();

        //2.2 获取所有本类声明的变量（不问访问权限）
        //Field[] fields = myClass.getDeclaredFields();

        //3. 遍历变量并输出变量信息
        System.out.println("访问权限" + "\t\t" + "变量类型" + "\t\t" + "变量名");
        for (Field f : fields) {
            //获取访问权限并输出
            int modifiers = f.getModifiers();
            System.out.print(Modifier.toString(modifiers) + "\t");
            //输出变量的类型及变量名
            System.out.println(f.getType().getName() + "\t" + f.getName());
        }
    }

    /**
     * 通过反射获取类的所有方法
     */
    private static void printMethods() {
        //1.获取并输出类的名称
        Class myClass = SonClass.class;
        System.out.println("类名称："+myClass);

        //2.1 获取所有 public 访问权限的方法
        //包括自己声明和从父类继承的
        //Method[] methods = myClass.getMethods();

        //2.2 获取所有本类的的方法（不问访问权限）
        Method[] methods=myClass.getDeclaredMethods();

        //3.遍历所有方法
        for (Method method :
                methods) {
            //获取并输出方法的访问权限（Modifiers：修饰符）
            int modifiers = method.getModifiers();
            System.out.print(Modifier.toString(modifiers)+" ");

            //获取并输出方法的返回值类型
            Class<?> returnType = method.getReturnType();
            System.out.print(returnType.getName()+" ");

            //获取并输出方法的方法名
            System.out.print(method.getName()+"( ");

            //获取并输出方法的所有参数
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter:
                    parameters) {
                System.out.print(parameter.getType().getName()+" "+parameter.getName()+",");
            }

            //获取并输出方法抛出的异常
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            if (exceptionTypes.length==0){
                System.out.println(" )");
            }else {
                for (Class c:
                        exceptionTypes) {
                    System.out.println(" ) throws "+c.getName());
                }
            }
        }
    }
}
