package com.cat;

/**
 *
 *
 * @author liu.zhi
 * @date 2020/11/17 9:51
 */
public class Generant {

    /**
     * 生产
     */
    public void produce() {
        while (true) {
            for (int i = 0; i < 10000; i++) {
                if (Demo2.signal){
                    Demo2.queue.add(String.valueOf(i));
                    if (Demo2.queue.size() > 9) {
                        Demo2.signal = false;
                    }
                }
            }
        }
    }
}
