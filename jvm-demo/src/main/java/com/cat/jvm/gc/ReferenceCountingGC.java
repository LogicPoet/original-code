package com.cat.jvm.gc;

/**
 * testGC()方法执行后， objA和objB会不会被GC呢？
 * VM Args: -XX:+PrintGCDetails 查询内存回收日志（-XX:+PrintGC 输出简单GC日志）
 * @author zzm
 * @date 2020/6/23 14:32
 */
public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024 * 1024;
    /**
     * 这个成员属性的唯一意义就是占点内存， 以便能在GC日志中看清楚是否有回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        // 假设在这行发生GC， objA和objB是否能被回收？
        System.gc();
    }

    public static void main(String[] args) {
        testGC();
    }
}
