package cn.catguild.reactor.official.threadscheduler;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * 线程调度器测试
 * <p>
 * 像RxJava一样，Reactor可以被视为与并发无关的。
 * 也就是说，它不强制执行并发模型。
 * 相反，它使您（开发人员）处于命令状态。但是，这不会阻止库帮助您进行并发。
 *
 * @author LZ
 * @date 2020/9/11 18:36
 **/
public class ThreadScheTest {

    @Test
    public void simpleTest() throws InterruptedException {
        final Mono<String> mono = Mono.just("hello "); //Mono <String>组装在线程main中。

        Thread t = new Thread(() -> mono
                .map(msg -> msg + "thread ")
                .subscribe(v -> //但是，它是在线程Thread-0中订阅的。
                        //结果，映射和onNext回调实际上都在线程-0中运行
                        System.out.println(v + Thread.currentThread().getName())
                )
        );
        t.start();
        t.join();
    }

    /**
     * publishOn在订户链的中间以与其他任何运算符相同的方式应用。
     * 它从上游获取信号并在下游回放它们，同时在关联的Scheduler的worker上执行回调。
     * 因此，它将影响后续运算符的执行位置（直到链接了另一个publishOn），如下所示：
     *
     * - 将执行上下文更改为调度程序选择的一个线程
     *
     * - 根据规范，onNext调用是按顺序发生的，因此这会占用一个线程
     *
     * - 除非他们在特定的Scheduler上工作，否则publishOn之后的运算符将继续在同一线程上执行
     */
    @Test
    public void publishOnTest() {
        // 创建一个由四个线程实例支持的新调度程序。
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> 10 + i)//第一个映射在<5>中的匿名线程上运行。
                .publishOn(s)//	publishOn将整个序列切换到从<1>选取的Thread上。
                .map(i -> "value " + i);//第二个映射在<1>的线程上运行。

        // 这个匿名线程就是进行订阅的地方。
        // 打印发生在最新的执行上下文中，该上下文是publishOn中的内容。
        new Thread(() -> flux.subscribe(System.out::println));
    }

    /**
     * 当构造了反向链时，subscribeOn适用于订阅过程。
     * 因此，无论您将subscribeOn放置在链中的什么位置，它始终会影响源发射的上下文。
     * 但是，这不会影响后续对publishOn的调用的行为-它们仍会在它们之后切换链中该部分的执行上下文。
     *
     * - 更改整个运营商链所订阅的线程
     *
     * - 从调度程序中选择一个线程
     *
     * ps: 实际上仅考虑链中最早的SubscribeOn调用。
     */
    @Test
    public void subscribeOn(){
        //	创建一个由四个线程支持的新调度程序。
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> 10 + i)//	第一个映射在这四个线程之一上运行...
                .subscribeOn(s)//…因为subscriptionOn会从订阅时间（<5>）开始切换整个序列。
                .map(i -> "value " + i);//第二个映射也运行在同一线程上。

        //这个匿名线程是最初进行预订的那个线程，但是subscribeOn立即将其转移到四个调度程序线程之一。
        new Thread(() -> flux.subscribe(System.out::println));
    }
}
