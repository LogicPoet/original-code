package com.cat;

/**
 * @author liu.zhi
 * @date 2020/11/17 9:51
 */
public class Consumer {

    /**
     * 消费
     */
    public void consume(){
        while (true) {
            if (!Demo2.signal){
                String poll = Demo2.queue.poll();
                System.out.println(poll);
                System.out.println("元素个数："+Demo2.queue.size());
                if (Demo2.queue.size()<1){
                    Demo2.signal = true;
                }
            }
        }
    }
}
