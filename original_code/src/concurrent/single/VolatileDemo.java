package concurrent.single;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Title: VolatileDemo</p>
 * <p>description: volatile关键字的示例代码</p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/9/7 20:02
 **/
public class VolatileDemo {
    public static void main(String[] args) throws InterruptedException {
        //验证可见性
        //VerifyVisibility();

        //验证原子性
        //for (int i = 0; i <100 ; i++) {
        //    verifyAtomicity();
        //}

        //解决volatile的原子操作问题
        //for (int i = 0; i <100 ; i++) {
        //    atomicSelfIncrease();
        //}

    }

    /**
     * 解决volatile的原子操作问题
     * 解决方案：
     *      使用juc包下的atomic**类
     *      对于整型使用atomicInteger.incrementAndGet()方法可以实现int型的原子性自增
     * 这个方法怎么实现原子操作？
     * CSA原理：Compare-And-Swap它是一条CPU并发原语
     *      1.CAS并发原语体现在JAVA语言中就是sun.misc.Unsafe类中的各个方法。调用UnSafe类中CAS方法
     *  Unsafe：是CAS的核心类，由于Java方法无法直接访问底层系统，需要通过本地（native）方法来访问，Unsafe相当于一个后门，基于该类可以直接操作特定内存的数据。
     */
    private static void atomicSelfIncrease() {
        Account account=new Account();
        for (int i = 0; i <20 ; i++) {
            new Thread(()->{
                for (int j = 0; j <1000 ; j++) {
                    account.increaseAtomic();
                }
            }).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println("===========================");
        System.out.println("main线程统计结果："+account.atomicInteger);
    }

    /**
     * 验证volatile的原子性
     * 思路：
     * 启用多个线程对数据进行自增操作（i++是非原子操作），然后让主线程统计结果值。这里启用20个线程，每个线程自增1000次，如果volatile保证原子性，那main线程结果为20000
     * 结果：
     * 1.多次执行后，main线程统计结果都小于20000
     * 2.会偶尔恰好等于20000
     * 结论：
     * volatile修饰的变量不能能保证原子性（会丢失操作）
     * 原因：
     * 当某个线程写完数据并通知其他线程时，恰好有某个线程在写入时被挂起，没有收到此次更新通知，会导致数据的覆盖，故最后统计的数据会低于20000期望值
     */
    private static void verifyAtomicity() {
        Account account = new Account();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    //自增操作
                    account.increase();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("==============================");
        System.out.println("main线程统计最后结果为：" + account.balance);
    }

    /**
     * 验证volatile的可见性
     * 思路：
     * 启用一个线程更新资源的值，在让主线程去读取，并判断是否与预期更新的值是否相同
     * 结果：
     * 不加volatile，更新线程顺利更新数据，主线程陷入死循环
     * 加volatile，更新线程顺利更新数据，主线程顺利读入数据，并正常退出
     * 结论：
     * volatile修饰的变量能保证可见性（让所有线程第一时间感知到数据的更新）
     * 注：
     * 1.所有的数据都在主内存中
     * 2.每个线程需要使用某个数据时，都会拷贝一个副本到自己的工作内存中（线程私有），当修改完成时便会写入主内存中。而可见性则是，当这个重新写入动作完成后会通知所有拷贝过这个数据的线程重新读取这个数据的最新值（原本是不会通知的）。
     * 3.主线程main只是一个特殊的线程而已，它操作的数据也是从主内存中拷贝而来的。
     */
    private static void verifyVisibility() {
        //创建资源类
        Account account = new Account();

        //线程操作资源类
        new Thread(() -> {
            //修改资源类的值
            account.balanceTo10();
            System.out.println(Thread.currentThread().getName() + "\t 已经更新了数据：" + account.balance);
        }, "aa").start();

        //主线程读取资源类的数据
        while (account.balance != 10) {

        }

        System.out.println(Thread.currentThread().getName() + "\t 已读取到最新值：" + account.balance);
    }
}

/**
 * 资源类
 */

class Account {

    volatile int balance = 0;

    volatile AtomicInteger atomicInteger=new AtomicInteger(0);

    public void increaseAtomic() {
        //以原子方式将当前值增加1
        atomicInteger.incrementAndGet();
        /*
        * getAndAddInt(当前对象, 内存偏移量, 1)
        * unsafe.getAndAddInt(this, valueOffset, 1) + 1;
        *
        * int var5;
        * do {
        *     var5 = this.getIntVolatile(var1, var2);
        * } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));比较并交换，
        * return var5;
        *
        * public native int getIntVolatile(Object var1, long var2);
        * public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
        * */

    }

    public void increase() {
        balance++;
    }

    public void balanceTo10() {
        balance = 10;
    }
}

/*
 * 1.是什么？
 *   轻量级的同步机制
 *
 * 2.使用了会怎么样
 *   保证可见性、禁止指令重排、注：不保证原子性
 *   2.2指令重排
 *      源代码->编译器优化的重排->指令并行的重排->内存系统的重排->最终执行的指令
 *      处理器在进行重排序时必须考虑指令之间的数据依赖性，换而言之，只要指令之间的数据没有相互依赖性，那么指令的执行顺序将难以确定
 *   2.3什么是原子性
 *      原子性是不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者分割，需要整体完成，要么同时成功，要么同时失败。
 *
 * 3.为什么产生
 *   作为synchronized和ReentrantLock的另一种选择
 *   提高程序的并发性
 *   注：需要解决原子性问题
 *
 * 4.什么时候使用
 *
 *
 * 5.JMM（Java内存模型，简称JMM）本身是一种抽象的概念并不真实存在，它描述的是一组规则或规范，通过这组规范定义了程序中各个变量（包括实例字段，静态字段和构成数组对象的元素）的访问方式。
 *
 * JMM关于同步的规定：
 * 1.线程解锁前，必须把共享变量的值刷新回主内存。
 * 2.线程加锁前，必须读取主内存的最新值到自己的工作内存。
 * 3.加锁解锁是同一把锁。
 *
 * */