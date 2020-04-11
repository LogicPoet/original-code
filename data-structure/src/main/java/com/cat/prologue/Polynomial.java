package com.cat.prologue;

import java.time.Duration;
import java.time.Instant;

/**
 * <p>Title: Polynomial</p>
 * <p>description: 计算多项式定点的值</p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/11/1 19:36
 **/
public class Polynomial {
    public static void main(String[] args) {
        double[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Instant start1 = Instant.now();
        for (int i = 0; i < 10000; i++) {
            double calculate = calculate1(9, a, 2);
            //double calculate = calculate2(9, a, 2);
        }
        Instant end1 = Instant.now();
        Duration duration = Duration.between(start1, end1);

        System.out.println("运行时间：" + duration.toMillis());
    }

    /**
     * 计算给的多项式，计算其定点值
     *
     * @param n 多项式的阶数
     * @param a 多项式系数数组
     * @param x 需要计算的定点值，比如f(1.5)中的1.5
     */
    private static double calculate1(int n, double[] a, double x) {
        //多项式f(x)=a0+a1x+a2x2...
        double p = a[0];
        for (int i = 1; i < n; i++) {
            p += a[i] * Math.pow(x, i);
        }
        return p;
    }

    /**
     * 计算给的多项式，计算其定点值
     *
     * @param n 多项式的阶数
     * @param a 多项式系数数组
     * @param x 需要计算的定点值，比如f(1.5)中的1.5
     * @return 结果
     */
    private static double calculate2(int n, double[] a, double x) {
        //多项式f(x)=a0+a1x+a2x^2+a3x^3+...
        //f(x)=a0+a1x+a2x^2+a3x^3+...anx^n
        double p = a[n];
        for (int i = n; i > 0; i--) {
            p+=a[i-1]+x*p;
        }
        return p;
    }
}
