package com.cat;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 *
 * @author liu.zhi
 * @date 2020/11/16 16:15
 */
public class Demo2 {
    public static int max = 10;
    public static boolean signal = true;
    public static Queue<String> queue = new LinkedBlockingQueue<>(max);

    public static void main(String[] args) {

        Resources<String> resources = new Resources<>();
        for (int i = 0; i < 20; i++) {
            boolean enter = resources.enter(String.valueOf(i));
            if (!enter){
                break;
            }
        }

        while (true){
            String leave = resources.leave();
            System.out.println(leave);
        }


    }

}
