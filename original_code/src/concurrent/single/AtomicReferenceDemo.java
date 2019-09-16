package concurrent.single;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>Title: AtomicReferenceDemo</p>
 * <p>description: 原子引用</p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/9/8 21:28
 **/
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();

        User z3 = new User("张三", 18);
        User li4 = new User("李四", 20);

        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString());
    }
}


class User {
    String username;
    int age;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
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
 */