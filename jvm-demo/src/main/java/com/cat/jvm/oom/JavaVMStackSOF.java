package com.cat.jvm.oom;

/**
 * 使用-Xss参数减少栈内存容量
 * 结果：抛出StackOverflowError异常，异常出现时输出的堆栈深度相应缩小
 * <p>
 * VM Args: -Xss128k
 * <p>
 * 不同版本的Java虚拟机和不同的操作系统， 栈容量最小值可能会有所限制， 这主要取决于操
 * 作系统内存分页大小。 譬如上述方法中的参数-Xss128k可以正常用于32位Windows系统下的JDK 6， 但
 * 是如果用于64位Windows系统下的JDK 11， 则会提示栈容量最小不能低于180K， 而在Linux下这个值则
 * 可能是228K， 如果低于这个最小限制， HotSpot虚拟器启动时会给出如下提示：
 * <p>
 * The Java thread stack size specified is too small. Specify at least 228k
 *
 * @author LZ
 * @date 2020/6/23 9:48
 **/
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
