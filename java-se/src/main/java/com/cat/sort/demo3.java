package com.cat.sort;

/**
 * @author liu.zhi
 * @date 2020/11/23 15:30
 */
public class demo3 {

    public static void main(String[] args) {
        demo3 demo3 = new demo3();
        String path = demo3.getClass().getClassLoader().getResource("1.txt").getPath();
        System.out.println(path);
    }
}
