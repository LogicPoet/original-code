
package com.cat.annotation.simple;

import com.cat.annotation.simple.MetaAnnotation;

/**
 * <p>Title: TestMeta</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/9/21 16:19
 **/

public class TestMeta {
    @MetaAnnotation
    public TestMeta() {
    }

    public void elementType() {

    }

    public static void main(String[] args) {
        //System.out.println(anInt());
        sun();

        int a=1;
        a=a++;
        int  b=a;
        System.out.println(b);
    }




    private static int anInt() {
        int a = 0;
        try {
            int i=1/0;
            System.out.println("执行try");
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("catch执行");
            return a;
        } finally {
            System.out.println("finally执行");
            System.out.println(a);
            return a;
        }
    }

    private static void sun(){
        int i=1;//1
        i=i++;//1
        System.out.println(i);
        int j=i++;//j=1
        System.out.println("--"+i);
        int k=i+ ++i * i++;//
        System.out.println("i= "+i);//4
        System.out.println("j= "+j);//3
        System.out.println("k= "+k);//15
         //
    }
}
