package com.cat;

/**
 * 资源
 *
 * @author liu.zhi
 * @date 2020/11/17 9:58
 */
public class Resources<E> {
    /**
     * 队列头指针
     */
    private int head = -1;
    /**
     * 队列容量
     */
    private int max = 10;
    /**
     * 单项队列
     */
    private final Object[] queue = new Object[max];

    /**
     * 进队,每次进队都放在队尾
     *
     * @return
     */
    public boolean enter(E element){
        if (head + 1 > max - 1){
            return false;
        }

        // 队列中是否有元素
        if (head > -1){
            // 存在元素,需要将队列元素整体往前移动
            for (int i = head; i > -1; i--) {
                queue[i+1] = queue[i];
            }
        }
        head += 1;

        // 元素每次都放在队尾
        queue[0] = element;
        return true;
    }

    /**
     * 出队
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public E leave(){
        E e = null;
        if (head < 0){
            throw new RuntimeException("队列已经全部出队完成");
        }
        int temp = head;
        head -= 1;
        e = (E)queue[temp];
        return e;
    }

    public void printAll(){
        System.out.print("当前队列元素为：");
        for (int i = 0; i < queue.length; i++) {
            System.out.print(queue[i]);
        }
        System.out.println();
    }

}
