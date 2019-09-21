package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>Title: ModifyClassInfo</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/9/21 9:08
 **/
public class ModifyClassInfo {
    public static void main(String[] args) throws Exception {
        //访问对象的私有方法
        //getPrivateMethod();

        //修改对象私有变量的值
        //modifyPrivateFiled();

        //修改对象私有常量的值
        //modifyFinalFiled();

    }

    /**
     * 访问对象的私有方法
     * 为简洁代码，在方法上抛出总的异常，实际开发别这样
     */
    private static void getPrivateMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //1. 获取 Class 类实例
        TestClass testClass = new TestClass();
        Class<? extends TestClass> myClass = testClass.getClass();

        //2. 获取私有方法
        //第一个参数为要获取的私有方法的名称
        //第二个为要获取方法的参数的类型，参数为 Class...，没有参数就是null
        //方法参数也可这么写 ：new Class[]{String.class , int.class}
        Method method = myClass.getDeclaredMethod("privateMethod", String.class, int.class);

        //3. 开始操作方法
        if (method != null) {
            //获取私有方法的访问权
            //只是获取访问权，并不是修改实际权限
            method.setAccessible(true);

            //使用 invoke 反射调用私有方法
            //privateMethod 是获取到的私有方法
            //testClass 要操作的对象
            //后面两个参数传实参
            method.invoke(testClass, "java reflect", 666);
        }
    }

    /**
     * 修改对象私有变量的值
     * 为简洁代码，在方法上抛出总的异常
     */
    private static void modifyPrivateFiled() throws NoSuchFieldException, IllegalAccessException {
        //1. 获取 Class 类实例
        TestClass testClass = new TestClass();
        Class<? extends TestClass> myClass = testClass.getClass();

        //2. 获取私有变量
        Field msg = myClass.getDeclaredField("msg");

        //3. 操作私有变量
        if (msg != null) {
            //获取私有变量的访问权
            msg.setAccessible(true);
            //输出私有变量未修改之前的值
            System.out.println("Before Modify：MSG = " + testClass.getMsg());

            //调用 set(object , value) 修改变量的值
            msg.set(testClass,"Modified");
            System.out.println("After Modify：MSG = " + testClass.getMsg());
        }
    }

    /**
     * 修改对象私有常量的值
     * 为简洁代码，在方法上抛出总的异常，实际开发别这样
     */
    private static void modifyFinalFiled() throws NoSuchFieldException, IllegalAccessException {
        //1. 获取 Class 类实例
        TestClass testClass=new TestClass();
        Class<? extends TestClass> myClass = testClass.getClass();
        //2. 获取私有常量
        Field final_value = myClass.getDeclaredField("FINAL_VALUE");

        //3. 修改常量的值
        if (final_value!=null){
            //获取私有常量的访问权
            final_value.setAccessible(true);

            //调用 finalField 的 getter 方法
            //输出 FINAL_VALUE 修改前的值
            System.out.println("Before Modify：FINAL_VALUE = "
                    + final_value.get(testClass));

            //修改私有常量
            final_value.set(testClass,"Modified");

            //调用 finalField 的 getter 方法
            //输出 FINAL_VALUE 修改后的值
            System.out.println("After Modify：FINAL_VALUE = "
                    + final_value.get(testClass));

            //使用对象调用类的 getter 方法
            //获取值并输出
            System.out.println("Actually ：FINAL_VALUE = "
                    + testClass.getFinalValue());
        }
    }
}
