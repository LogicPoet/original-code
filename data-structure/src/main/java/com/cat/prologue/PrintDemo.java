package com.cat.prologue;

import java.time.Duration;
import java.time.Instant;

/**
 * <p>Title: PrintDemo</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/11/1 17:53
 **/
public class PrintDemo {
    /**
     * 解决问题方法的效率，跟空间的利用率有关
     * @param args
     */
    public static void main(String[] args) {
        //参数值
        //100,1000,10000
        int N=10000;

        Instant start1 = Instant.now();
        printN1(N);
        Instant end1 = Instant.now();
        System.out.println();

        Instant start2 = Instant.now();
        printN2(N);
        Instant end2 = Instant.now();

        Duration timeElapsed1=Duration.between(start1,end1);
        long millis1=timeElapsed1.toMillis();
        Duration timeElapsed2=Duration.between(start2,end2);
        long millis2=timeElapsed2.toMillis();

        System.out.println("普通实现耗时："+millis1);
        System.out.println("递归实现耗时："+millis2);
    }


    /**
     * 普通实现
     *
     * @param N 正整数
     */
    private static void printN1(int N) {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " ");
        }
    }

    /**
     * 递归实现
     *
     * @param N 正整数
     */
    private static void printN2(int N) {
        if (N != 0) {
            //如果不是第一个数，就递归输出这个数
            printN2(N - 1);
            System.out.print(N + " ");
        }
    }
}
